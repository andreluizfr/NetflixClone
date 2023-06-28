package com.example.NetflixClone.Controllers.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Auth.LoginBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.CustomExceptions.FailToFindUserException;
import com.example.NetflixClone.CustomExceptions.UnmatchedPasswordException;
import com.example.NetflixClone.Models.User;

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:5173" }, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    LoginBusiness loginBusiness;

    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO data) {

        try {
            User user = loginBusiness.execute(data);

            return ResponseErrorHandler.generateResponse("Login realizado com sucesso.",
                    HttpStatus.OK,
                    user);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.BAD_REQUEST, null,
                    FailToFindUserException.getErrorCode());

        } catch (FailToFindUserException e) {

            System.out.println(e.getMessage());

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_FOUND, null,
                    FailToFindUserException.getErrorCode());

        } catch (UnmatchedPasswordException e) {

            System.out.println(e.getMessage());

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_ACCEPTABLE, null,
                    UnmatchedPasswordException.getErrorCode());

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

    }
    
}
