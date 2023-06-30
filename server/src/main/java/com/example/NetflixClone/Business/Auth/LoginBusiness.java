package com.example.NetflixClone.Business.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Controllers.Auth.LoginDTO;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class LoginBusiness {
    @Autowired
    UserRepositoryDAO userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public String execute(LoginDTO data) throws IllegalArgumentException, AuthenticationException{

        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = this.authenticationManager.authenticate(emailPassword);

        String accessToken = tokenService.generateToken((User) auth.getPrincipal());
        
        return accessToken;

    }

}
