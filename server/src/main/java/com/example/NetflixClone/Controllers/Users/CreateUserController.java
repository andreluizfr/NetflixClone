package com.example.NetflixClone.Controllers.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Users.CreateUserBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.CustomExceptions.FailToCreateUserException;
import com.example.NetflixClone.Models.User;

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:5173" }, allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
public class CreateUserController {

    @Autowired
    CreateUserBusiness createUserBusiness;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO data) {

        try {
            User newUser = createUserBusiness.execute(data);

            return ResponseErrorHandler.generateResponse("Conta criada com sucesso.", HttpStatus.CREATED,
                    newUser);

        } catch (FailToCreateUserException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null,
                    FailToCreateUserException.getErrorCode());
        }

    }

}
