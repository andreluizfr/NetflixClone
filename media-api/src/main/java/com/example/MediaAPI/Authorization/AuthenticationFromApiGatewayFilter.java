package com.example.MediaAPI.Authorization;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.MediaAPI.Authorization.Models.CheckEmailResponse;
import com.example.MediaAPI.Authorization.Models.User;
import com.example.MediaAPI.Authorization.Models.UserClient;
import com.example.MediaAPI.Util.TokenUtils;

import feign.FeignException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFromApiGatewayFilter extends OncePerRequestFilter {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserClient userClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String email = request.getHeader("X-Logged-In-User");
        
        if(email != null && email.length() > 0) {
            try{
                CheckEmailResponse checkEmailResponse = userClient.checkEmail(email);

                User user = new User();
                user.setEmail(email);
                user.setId(checkEmailResponse.getData());

                var token = tokenUtils.recoverToken(request);

                List<String> permissions = tokenUtils.getPermissionsFromToken(token);

                List<CustomPermission> authorities = permissions
                    .stream()
                    .map(permission -> new CustomPermission(permission))
                    .collect(Collectors.toList());

                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (FeignException e) {
                e.printStackTrace();
            } catch (JWTVerificationException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }

}
