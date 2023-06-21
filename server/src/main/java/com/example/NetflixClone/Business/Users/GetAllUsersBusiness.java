package com.example.NetflixClone.Business.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.CustomExceptions.FailToGetAllUsersException;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class GetAllUsersBusiness {
    @Autowired
    UserRepositoryDAO userRepository;

    public List<User> execute() throws FailToGetAllUsersException {

        try {
            List<User> user = userRepository.findAll();

            return user;

        } catch (RuntimeException e) {

            throw new FailToGetAllUsersException(e.getMessage());
        }

    }
}
