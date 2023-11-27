package com.example.ApiGateway.Authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiGateway.Authentication.DataProvider.UserRepository;
import com.example.ApiGateway.Authentication.Models.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenSecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {

            String email;

            try {
                email = tokenService.validateToken(token);

                User user =  userRepository.findByEmail(email);

                var authentication = new UsernamePasswordAuthenticationToken((UserDetails) user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

                var requestWrapper = new ModifiedHeaderRequestWrapper(request, user.getEmail());

                request = (HttpServletRequest) requestWrapper.getRequest();

            } catch (JWTVerificationException e) {

                e.printStackTrace();

            } catch (RuntimeException e) {

                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null)
            return null;

        return authHeader.replace("Bearer ", "");
    }

}
