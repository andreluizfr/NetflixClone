package com.example.UserService.Account.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.Account.Business.AccountBusiness;
import com.example.UserService.Account.Controller.Models.UpdateAccountPlanDTO;
import com.example.UserService.Account.Exceptions.FailToFindAccountException;
import com.example.UserService.Account.Models.Account;
import com.example.UserService.Util.ResponseHandler;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountBusiness accountBusiness;

    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getAccount(@RequestParam(name = "id", required = false) UUID id)
            throws FailToFindAccountException, IllegalArgumentException {

        Account account = accountBusiness.getAccount(id);

        return ResponseHandler.generateResponse("Conta buscada com sucesso.",
                HttpStatus.OK,
                account);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAccount() {

        List<Account> accounts = accountBusiness.getAllAccounts();

        return ResponseHandler.generateResponse("Todas as contas buscada com sucesso.",
                HttpStatus.OK,
                accounts);
    }

    @PostMapping("/updatePlan")
    public ResponseEntity<Object> updateAccountPlan(@RequestBody UpdateAccountPlanDTO data)
            throws IllegalArgumentException, FailToFindAccountException {

        Account updatedAccount = accountBusiness.updateAccountPlan(data);

        return ResponseHandler.generateResponse("Plano da conta atualizado com sucesso.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                updatedAccount);
    }

    @ExceptionHandler(FailToFindAccountException.class)
    public ResponseEntity<Object> unkownError(FailToFindAccountException e) {
        e.printStackTrace();
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
