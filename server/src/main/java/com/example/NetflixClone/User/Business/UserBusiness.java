package com.example.NetflixClone.User.Business;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.NetflixClone.Account.DataProvider.AccountRepository;
import com.example.NetflixClone.Account.Models.Account;
import com.example.NetflixClone.Exceptions.FailToFindUserException;
import com.example.NetflixClone.User.Controller.Models.CreateUserDTO;
import com.example.NetflixClone.User.DataProvider.UserRepository;
import com.example.NetflixClone.User.Models.User;
import com.example.NetflixClone.User.Models.Enums.UserRole;

@Service
public class UserBusiness {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public User createUser(CreateUserDTO data)
            throws IllegalAccessException, DataIntegrityViolationException, DateTimeParseException {

        if (data.email() == null || data.password() == null || data.birthDate() == null)
            throw new IllegalArgumentException("Error: email, password or birthDate not found");

        Account account = new Account();
        Account newAccount = accountRepository.save(account);

        User user = new User();
        user.setEmail(data.email());
        user.setPassword(data.password());
        user.setBirthDate(data.birthDate());
        user.setAccount(newAccount);
        user.setRole(UserRole.BASIC);

        User newUser = userRepository.save(user);

        return newUser;
    }

    @Transactional
    public User fetchUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {

            User user = (User) authentication.getPrincipal();

            Hibernate.initialize(user.getAccount().getProfiles());

            return user;
        }

        throw new RuntimeException("Error: Nenhum usuário válido autenticado encontrado no contexto");
    }

    @Transactional
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @Transactional
    public User getUser(UUID id) throws IllegalArgumentException, FailToFindUserException {

        if (id == null)
            throw new IllegalArgumentException("Error: id is null.");

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent())
            return optionalUser.get();

        throw new FailToFindUserException("O id " + id + " não pertence a nenhum usuário registrado.");
    }
}
