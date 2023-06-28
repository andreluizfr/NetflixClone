package com.example.NetflixClone.Business.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Controllers.Auth.LoginDTO;
import com.example.NetflixClone.CustomExceptions.FailToFindUserException;
import com.example.NetflixClone.CustomExceptions.UnmatchedPasswordException;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class LoginBusiness {
    @Autowired
    UserRepositoryDAO userRepository;

    public User execute(LoginDTO data) throws IllegalArgumentException, FailToFindUserException, UnmatchedPasswordException {

        if(data.email() == null) throw new IllegalArgumentException("email is null");
        if(data.password() == null) throw new IllegalArgumentException("password is null");

        Optional<User> optionalUser = userRepository.findOneByEmail(data.email());

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if(user.validatePassword(data.password())) return user;

            else throw new UnmatchedPasswordException("A senha não combinou com a do usuário buscado no banco.");

        } else {

            throw new FailToFindUserException("email não registrado.");
        }

    }

}
