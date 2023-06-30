package com.example.NetflixClone.Controllers.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Users.FetchUserBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.Models.User;

@RestController
@RequestMapping("/api/user")
public class FetchUserController {

    @Autowired
    FetchUserBusiness fetchUserBusiness;

    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchUser() {

        try {
            User user = fetchUserBusiness.execute();

            return ResponseErrorHandler.generateResponse("Dados buscados com sucesso.", HttpStatus.OK,
                    user);

        } catch (RuntimeException e) {

            System.out.println(e.getMessage());

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } 
    }
    
}
