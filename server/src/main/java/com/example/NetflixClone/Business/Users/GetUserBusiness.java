package com.example.NetflixClone.Business.Users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.CustomExceptions.FailToGetUserException;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class GetUserBusiness {
    @Autowired
    UserRepositoryDAO userRepository;

    public User execute(UUID id) throws FailToGetUserException {

        try {
            
            Optional<User> optionalUser = userRepository.findById(id);

            if(optionalUser.isPresent()) return optionalUser.get();
            
            throw new FailToGetUserException("O id não pertence a nenhum usuário registrado.");

        } catch (RuntimeException e) {

            throw new FailToGetUserException(e.getMessage());
        }

    }
}
