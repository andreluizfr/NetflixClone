package com.example.UserAPI.User.Business;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.UserAPI.Account.DataProvider.AccountRepository;
import com.example.UserAPI.User.Exceptions.FailToFindUserException;
import com.example.UserAPI.Account.Models.Account;
import com.example.UserAPI.Profile.Models.Profile;
import com.example.UserAPI.User.Controller.Models.CreateUserDTO;
import com.example.UserAPI.User.DataProvider.UserRepository;
import com.example.UserAPI.User.Models.User;
import com.example.UserAPI.User.Models.Enums.UserRole;
import com.example.UserAPI.UserActivity.Models.UserActivityDTO;
import com.google.gson.Gson;

@Service
public class UserBusiness {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    Gson gson;

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

        this.sendUserCreatedTopic(newUser);
        this.sendSaveUserActivityTopic(newUser, "CREATE_USER", null);

        return newUser;
    }

    private void sendUserCreatedTopic(User user) {
        kafkaTemplate.send("user-created", gson.toJson(user));
    }

    private void sendSaveUserActivityTopic(User user, String permission, Profile profile) {

        UserActivityDTO userActivityDTO = new UserActivityDTO();
        userActivityDTO.setUser(user);
        userActivityDTO.setPermissionName(permission);
        userActivityDTO.setProfile(profile);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        userActivityDTO.setIp(ip);

        kafkaTemplate.send("save-user-activity", gson.toJson(userActivityDTO));
    }

    @Transactional
    public User fetchUser(String email)
            throws FailToFindUserException, IllegalArgumentException {

        if (email == null)
            throw new IllegalArgumentException("O e-mail está nulo.");

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return user;
        }

        throw new FailToFindUserException("O e-mail " + email + " não pertence a nenhum usuário registrado.");
    }

    @Transactional
    @Cacheable(cacheNames = "smallTimeCache")
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @Transactional
    public User getUser(UUID id)
            throws IllegalArgumentException, FailToFindUserException {

        if (id == null)
            throw new IllegalArgumentException("Id is null.");

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent())
            return optionalUser.get();

        throw new FailToFindUserException("O id " + id + " não pertence a nenhum usuário registrado.");
    }

    @Transactional
    public boolean checkEmail(String email)
            throws IllegalArgumentException, FailToFindUserException {

        if (email == null)
            throw new IllegalArgumentException("Email is null.");

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()){

            User user = optionalUser.get();

            if(user.getEnabled() && !user.getDeleted())
                return true;
        }

        return false;
    }
}
