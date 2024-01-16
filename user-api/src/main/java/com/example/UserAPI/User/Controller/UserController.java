package com.example.UserAPI.User.Controller;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserAPI.User.Exceptions.FailToFindUserException;
import com.example.UserAPI.User.Business.UserBusiness;
import com.example.UserAPI.User.Controller.Models.CreateUserDTO;
import com.example.UserAPI.User.Models.User;
import com.example.UserAPI.Util.ResponseHandler;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserBusiness userBusiness;

    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getUser(@RequestParam(name = "id", required = true) UUID id)
            throws IllegalArgumentException, FailToFindUserException {

        User user = userBusiness.getUser(id);

        return ResponseHandler.generateResponse("Usuário buscado com sucesso.",
                HttpStatus.OK,
                user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUser() {

        List<User> users = userBusiness.getAllUsers();

        return ResponseHandler.generateResponse("Usuários buscados com sucesso.",
                HttpStatus.OK, users);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO data)
            throws IllegalAccessException, DataIntegrityViolationException, DateTimeParseException {

        User newUser = userBusiness.createUser(data);

        return ResponseHandler.generateResponse("Conta criada com sucesso.", HttpStatus.CREATED,
                newUser);
    }

    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchUser(@RequestHeader("X-Logged-In-User") String userEmail)
            throws FailToFindUserException, IllegalArgumentException {

        User user = userBusiness.fetchUser(userEmail);

        return ResponseHandler.generateResponse("Dados de usuário buscados com sucesso.", HttpStatus.OK,
                user);
    }

    @ExceptionHandler(FailToFindUserException.class)
    public ResponseEntity<Object> unkownError(FailToFindUserException e) {
        System.out.println(e.getMessage());
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
