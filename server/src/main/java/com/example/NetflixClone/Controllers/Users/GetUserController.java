package com.example.NetflixClone.Controllers.Users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Users.GetUserBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.CustomExceptions.FailToGetUserException;
import com.example.NetflixClone.Models.User;

@RestController
@RequestMapping("/api/user")
public class GetUserController {

    @Autowired
    GetUserBusiness getUserBusiness;

    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getUser(@RequestParam(name = "id", required = false) UUID id) {

        try {
            User user = getUserBusiness.execute(id);

            return ResponseErrorHandler.generateResponse("Usuário buscado com sucesso.",
                HttpStatus.OK,
                user);

        } catch (FailToGetUserException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, null,
                    FailToGetUserException.getErrorCode());

        }

    }

}
