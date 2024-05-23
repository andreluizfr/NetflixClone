package com.example.UserAPI.User.Controller;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserBusiness userBusiness;

    @Autowired
    Gson gson;

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
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO data, HttpServletRequest request)
            throws IllegalAccessException, DataIntegrityViolationException, DateTimeParseException {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) {
            ip = request.getHeader("X_FORWARDED_FOR");
            if (ip == null) {
                ip = request.getRemoteAddr();
            }
        }

        User newUser = userBusiness.createUser(data, ip);

        return ResponseHandler.generateResponse("Conta criada com sucesso.", HttpStatus.CREATED,
                newUser);
    }

    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchUser()
            throws FailToFindUserException, IllegalArgumentException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userAuthenticated = authentication != null ? (User) authentication.getPrincipal() : null;

        if (userAuthenticated != null) {
            User user = userBusiness.fetchUser(userAuthenticated.getEmail());

            return ResponseHandler.generateResponse("Dados de usuário buscados com sucesso.", HttpStatus.OK,
                    user);
        } else {
            return ResponseHandler.generateResponse("Usuário não autenticado.", HttpStatus.FORBIDDEN,
                    null);
        }
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Object> checkEmail(@RequestHeader("X-Logged-In-User") String email)
            throws FailToFindUserException, IllegalArgumentException {

        boolean check = userBusiness.checkEmail(email);

        return ResponseHandler.generateResponse("Email checado com sucesso.", HttpStatus.OK,
                check);
    }

    @ExceptionHandler(FailToFindUserException.class)
    public ResponseEntity<Object> unkownError(FailToFindUserException e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
