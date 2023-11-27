package com.example.UserService.User.Business;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.UserService.Account.DataProvider.AccountRepository;
import com.example.UserService.User.Exceptions.FailToFindUserException;
import com.example.UserService.Account.Models.Account;
import com.example.UserService.Schemas.CreateMessageSchema;
import com.example.UserService.User.Controller.Models.CreateUserDTO;
import com.example.UserService.User.DataProvider.UserRepository;
import com.example.UserService.User.Models.User;
import com.example.UserService.User.Models.Enums.UserRole;

@Service
public class UserBusiness {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    KafkaTemplate<String, CreateMessageSchema> kafkaTemplate;

    @Transactional
    public User createUser(CreateUserDTO data)
            throws IllegalAccessException, DataIntegrityViolationException, DateTimeParseException {

        if (data.email() == null || data.password() == null || data.birthDate() == null)
            throw new IllegalArgumentException("E-mail, password or birthDate not found");

        Account account = new Account();
        Account newAccount = accountRepository.save(account);

        User user = new User();
        user.setEmail(data.email());
        user.setPassword(data.password());
        user.setBirthDate(data.birthDate());
        user.setAccount(newAccount);
        user.setRole(UserRole.USER);

        User newUser = userRepository.save(user);
        
        this.sendNewUserEmail(newUser);

        return newUser;
    }

    private void sendNewUserEmail(User user) {

        CreateMessageSchema message = CreateMessageSchema.newBuilder()
            .setUserEmail(user.getEmail())
            .setMessageType(0)
            .setDeliveryMethod(0)
            .build();
            
        kafkaTemplate.send("user-created", message);
    }

    public User fetchUser(UUID id) 
            throws FailToFindUserException {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            Hibernate.initialize(user.getAccount().getProfiles());

            return user;
        } 

        throw new FailToFindUserException("O id " + id + " não pertence a nenhum usuário registrado.");
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
