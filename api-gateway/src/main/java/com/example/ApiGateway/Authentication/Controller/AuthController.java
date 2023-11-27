package com.example.ApiGateway.Authentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiGateway.Authentication.Business.AuthBusiness;
import com.example.ApiGateway.Authentication.Controller.Models.LoginDTO;
import com.example.ApiGateway.Util.ResponseHandler;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthBusiness authBusiness;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO data) {

        try {
            String accessToken = authBusiness.login(data);

            return ResponseHandler.generateResponse("Login realizado com sucesso, você será redirecionado em breve.",
                    HttpStatus.OK,
                    accessToken);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.BAD_REQUEST, null);

        } catch (AuthenticationException e) {

            System.out.println("Error: " + e.getMessage());

            return ResponseHandler.generateResponse("Error: E-mail ou senha errada",
                    HttpStatus.FORBIDDEN, null);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

    }

}
