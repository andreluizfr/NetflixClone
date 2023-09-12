package com.example.NetflixClone.User.Controller;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Exceptions.FailToFindUserException;
import com.example.NetflixClone.User.Business.UserBusiness;
import com.example.NetflixClone.User.Controller.Models.CreateUserDTO;
import com.example.NetflixClone.User.Models.User;
import com.example.NetflixClone.Util.ResponseHandler;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserBusiness userBusiness;

    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getUser(@RequestParam(name = "id", required = false) UUID id) {

        try {
            User user = userBusiness.getUser(id);

            return ResponseHandler.generateResponse("Usuário buscado com sucesso.",
                    HttpStatus.OK,
                    user);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (FailToFindUserException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_FOUND, null,
                    FailToFindUserException.getErrorCode());

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUser() {

        try {
            List<User> users = userBusiness.getAllUsers();

            return ResponseHandler.generateResponse("Usuários buscados com sucesso.",
                    HttpStatus.OK, users);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO data) {

        try {
            User newUser = userBusiness.createUser(data);

            return ResponseHandler.generateResponse("Conta criada com sucesso.", HttpStatus.CREATED,
                    newUser);

        } catch (IllegalAccessException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (DateTimeParseException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (DataIntegrityViolationException e) {

            Throwable cause = e.getMostSpecificCause();

            if (cause instanceof PSQLException) {

                PSQLException pSQLException = (PSQLException) cause;

                if (pSQLException.getErrorCode() == 0) // unica chave com unique é email
                    return ResponseHandler.generateResponse("Error: email já cadastrado",
                            HttpStatus.UNPROCESSABLE_ENTITY, null);
            }

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchUser() {

        try {
            User user = userBusiness.fetchUser();

            return ResponseHandler.generateResponse("Dados de usuário buscados com sucesso.", HttpStatus.OK,
                    user);

        } catch (RuntimeException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }

}
