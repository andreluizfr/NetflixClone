package com.example.NetflixClone.Controllers.Accounts;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Accounts.GetAccountBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.CustomExceptions.FailToGetAccountException;
import com.example.NetflixClone.CustomExceptions.FailToGetUserException;
import com.example.NetflixClone.Models.Account;

@RestController
@RequestMapping("/api/account")
public class GetAccountController {

    @Autowired
    GetAccountBusiness getAccountBusiness;

    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getAccount(@RequestParam(name = "id", required = false) UUID id) {

        try {
            Account account = getAccountBusiness.execute(id);

            return ResponseErrorHandler.generateResponse("Conta buscada com sucesso.",
                HttpStatus.OK,
                account);

        } catch (FailToGetAccountException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.BAD_REQUEST, null,
                    FailToGetUserException.getErrorCode());

        }

    }

}
