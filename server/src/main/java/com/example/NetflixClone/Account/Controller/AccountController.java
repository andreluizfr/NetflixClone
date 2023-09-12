package com.example.NetflixClone.Account.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Account.Business.AccountBusiness;
import com.example.NetflixClone.Account.Controller.Models.UpdateAccountPlanDTO;
import com.example.NetflixClone.Account.Models.Account;
import com.example.NetflixClone.Exceptions.FailToFindAccountException;
import com.example.NetflixClone.Exceptions.FailToFindUserException;
import com.example.NetflixClone.Util.ResponseHandler;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountBusiness accountBusiness;

    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getAccount(@RequestParam(name = "id", required = false) UUID id) {

        try {
            Account account = accountBusiness.getAccount(id);

            return ResponseHandler.generateResponse("Conta buscada com sucesso.",
                    HttpStatus.OK,
                    account);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.BAD_REQUEST, null,
                    FailToFindUserException.getErrorCode());

        } catch (FailToFindAccountException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_FOUND, null,
                    FailToFindUserException.getErrorCode());

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, null,
                    FailToFindUserException.getErrorCode());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAccount() {

        try {
            List<Account> accounts = accountBusiness.getAllAccounts();

            return ResponseHandler.generateResponse("Todas as contas buscada com sucesso.",
                    HttpStatus.OK,
                    accounts);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/updatePlan")
    public ResponseEntity<Object> updateAccountPlan(@RequestBody UpdateAccountPlanDTO data) {

        try {
            Account updatedAccount = accountBusiness.updateAccountPlan(data);

            return ResponseHandler.generateResponse("Plano da conta atualizado com sucesso.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    updatedAccount);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (FailToFindAccountException e) {

            System.out.println(e.getMessage());

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null,
                    FailToFindAccountException.getErrorCode());
        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
