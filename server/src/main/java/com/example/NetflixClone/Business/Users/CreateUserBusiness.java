package com.example.NetflixClone.Business.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Controllers.Users.CreateUserDTO;
import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Models.UserDTO;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class CreateUserBusiness {

    @Autowired
    UserRepositoryDAO userRepository;

    @Autowired
    AccountRepositoryDAO accountRepository;

    public User execute(CreateUserDTO data) throws IllegalAccessException, DataIntegrityViolationException {

        if (data.email() == null || data.password() == null || data.birthDate() == null)
            throw new IllegalArgumentException("Error: email, password or birthDate not found");

        Account account = new Account();
        Account newAccount = accountRepository.save(account);

        UserDTO newUserDTO = new UserDTO(data.email(), data.password(), data.birthDate(), newAccount);
        User user = new User(newUserDTO);

        User newUser = userRepository.save(user);

        return newUser;
    }
}
