package com.example.NetflixClone.Business.Users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.CustomExceptions.FailToFindUserException;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class GetUserBusiness {
    @Autowired
    UserRepositoryDAO userRepository;

    public User execute(UUID id) throws IllegalArgumentException, FailToFindUserException {

        if(id == null) throw new IllegalArgumentException("Error: id is null.");
            
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) return optionalUser.get();
        
        throw new FailToFindUserException("O id " + id + " não pertence a nenhum usuário registrado.");

    }
}
