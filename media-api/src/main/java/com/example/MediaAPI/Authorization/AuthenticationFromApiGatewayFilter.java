package com.example.MediaAPI.Authorization;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.MediaAPI.Authorization.DataProvider.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = tokenUtils.recoverToken(request);
            String email = tokenUtils.getEmailFromToken(token);

            if (email != null && email.length() > 0) {

                Optional<User> optionalUser = userRepository.findByEmail(email);

                if (optionalUser.isPresent()) {

                    User user = optionalUser.get();

                    List<String> permissions = tokenUtils.getPermissionsFromToken(token);

                    List<CustomPermission> authorities = permissions
                            .stream()
                            .map(permission -> new CustomPermission(permission))
                            .collect(Collectors.toList());

                    var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (FeignException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
