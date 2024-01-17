package com.example.UserAPI.User.Business;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.UserAPI.Account.DataProvider.AccountRepository;
import com.example.UserAPI.User.Exceptions.FailToFindUserException;
import com.example.UserAPI.Account.Models.Account;
import com.example.UserAPI.User.Controller.Models.CreateUserDTO;
import com.example.UserAPI.User.DataProvider.UserRepository;
import com.example.UserAPI.User.Models.User;
import com.example.UserAPI.User.Models.Enums.UserRole;

@Service
public class UserBusiness {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public User createUser(CreateUserDTO data)
            throws IllegalAccessException, DataIntegrityViolationException, DateTimeParseException {

        if (data.getEmail() == null || data.getPassword() == null || data.getBirthDate() == null)
            throw new IllegalArgumentException("E-mail, password or birthDate not found");

        Account account = new Account();
        Account newAccount = accountRepository.save(account);

        User user = new User();
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setBirthDate(data.getBirthDate());
        user.setAccount(newAccount);
        user.setRole(UserRole.USER);

        User newUser = userRepository.save(user);

        this.sendNewUserEmail(newUser);

        return newUser;
    }

    private void sendNewUserEmail(User user) {

        String message = user.getEmail();

        kafkaTemplate.send("user-created", message);
    }

    public User fetchUser(String email)
            throws FailToFindUserException, IllegalArgumentException {

        if (email == null)
            throw new IllegalArgumentException("O e-mail está nulo.");

        Optional<User> optionalUser = userRepository.findOneByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return user;
        }

        throw new FailToFindUserException("O e-mail " + email + " não pertence a nenhum usuário registrado.");
    }

    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    public User getUser(UUID id)
            throws IllegalArgumentException, FailToFindUserException {

        if (id == null)
            throw new IllegalArgumentException("Id is null.");

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent())
            return optionalUser.get();

        throw new FailToFindUserException("O id " + id + " não pertence a nenhum usuário registrado.");
    }
}
