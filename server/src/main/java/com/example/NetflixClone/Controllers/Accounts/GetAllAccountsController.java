package com.example.NetflixClone.Controllers.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Accounts.GetAllAccountsBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.Models.Account;

@RestController
@RequestMapping("/api/account")
public class GetAllAccountsController {
    @Autowired
    GetAllAccountsBusiness getAllAccountsBusiness;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAccount() {

        try {
            List<Account> accounts = getAllAccountsBusiness.execute();

            return ResponseErrorHandler.generateResponse("Todas as contas buscada com sucesso.",
                    HttpStatus.OK,
                    accounts);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

    }
}
