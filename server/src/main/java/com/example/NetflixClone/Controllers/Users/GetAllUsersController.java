package com.example.NetflixClone.Controllers.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Users.GetAllUsersBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.Models.User;

@RestController
@RequestMapping("/api/user")
public class GetAllUsersController {

    @Autowired
    GetAllUsersBusiness getAllUsersBusiness;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUser() {

        try {
            List<User> users = getAllUsersBusiness.execute();

            return ResponseErrorHandler.generateResponse("Usuários buscados com sucesso.",
                    HttpStatus.OK, users);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }
}
