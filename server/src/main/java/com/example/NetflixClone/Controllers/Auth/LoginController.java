package com.example.NetflixClone.Controllers.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Auth.LoginBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    LoginBusiness loginBusiness;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO data) {

        try {
            String accessToken = loginBusiness.execute(data);

            return ResponseErrorHandler.generateResponse("Login realizado com sucesso.",
                    HttpStatus.OK,
                    accessToken);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.BAD_REQUEST, null);

        } catch (AuthenticationException e) {

            System.out.println("Error: " + e.getMessage());

            return ResponseErrorHandler.generateResponse("Error: E-mail ou senha errada",
                    HttpStatus.FORBIDDEN, null);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

    }

}
