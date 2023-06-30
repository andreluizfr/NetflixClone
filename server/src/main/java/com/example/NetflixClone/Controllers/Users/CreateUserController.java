package com.example.NetflixClone.Controllers.Users;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Users.CreateUserBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.Models.User;

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:5173" })
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

        } catch (IllegalAccessException e) {

            System.out.println(e.getMessage());

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (DataIntegrityViolationException e) {

            Throwable cause = e.getMostSpecificCause();

            if (cause instanceof PSQLException) {

                PSQLException pSQLException = (PSQLException) cause;

                if (pSQLException.getErrorCode() == 0) // unica chave com unique é email
                    return ResponseErrorHandler.generateResponse("Error: email já cadastrado",
                            HttpStatus.UNPROCESSABLE_ENTITY, null);

            }

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

}
