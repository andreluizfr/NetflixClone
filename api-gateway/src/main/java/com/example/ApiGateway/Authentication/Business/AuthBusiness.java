package com.example.ApiGateway.Authentication.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ApiGateway.Authentication.Controller.Models.LoginDTO;
import com.example.ApiGateway.Authentication.DataProvider.UserRepository;
import com.example.ApiGateway.Authentication.Exceptions.AuthenticationException;
import com.example.ApiGateway.Authentication.Models.User;

@Service
public class AuthBusiness {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Transactional

    public String login(LoginDTO data) throws IllegalArgumentException, AuthenticationException {

        User user = userRepository.findByEmail(data.getEmail());

        if (user != null) {
            String accessToken = tokenService.generateToken(user);

            return accessToken;
        }

        throw new AuthenticationException();
    }
}
