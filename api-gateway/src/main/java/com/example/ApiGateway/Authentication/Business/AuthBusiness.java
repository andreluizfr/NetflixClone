package com.example.ApiGateway.Authentication.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ApiGateway.Authentication.TokenService;
import com.example.ApiGateway.Authentication.Controller.Models.LoginDTO;
import com.example.ApiGateway.Authentication.DataProvider.UserRepository;
import com.example.ApiGateway.Authentication.Models.User;

@Service
public class AuthBusiness {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Transactional
    public String login(LoginDTO data) throws IllegalArgumentException, AuthenticationException {

        System.out.println(data.email() + " " + data.password());

        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = this.authenticationManager.authenticate(emailPassword);

        String accessToken = tokenService.generateToken((User) auth.getPrincipal());

        return accessToken;

    }

}
