package com.example.NetflixClone.Business.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Controllers.Users.CreateUserDTO;
import com.example.NetflixClone.CustomExceptions.FailToCreateUserException;
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

    public User execute(CreateUserDTO data) throws FailToCreateUserException {

        Account newAccount;
        User newUser;

        try {
            Account account = new Account();
            newAccount = accountRepository.save(account);

            UserDTO newUserDTO = new UserDTO(data.email(), data.password(), data.birthDate(), newAccount);
            User user = new User(newUserDTO);
            newUser = userRepository.save(user);

            return newUser;

        } catch (RuntimeException e) {

            throw new FailToCreateUserException(e.getMessage());
        }

    }
}
