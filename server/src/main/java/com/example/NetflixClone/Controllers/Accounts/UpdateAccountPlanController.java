package com.example.NetflixClone.Controllers.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.Accounts.UpdateAccountPlanBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.CustomExceptions.FailToCreateUserException;
import com.example.NetflixClone.CustomExceptions.FailToUpdateAccountPlanException;
import com.example.NetflixClone.Models.Account;

@RestController
@RequestMapping("/api/account")
public class UpdateAccountPlanController {

    @Autowired
    UpdateAccountPlanBusiness updateAccountPlanBusiness;

    @PostMapping("/updatePlan")
    public ResponseEntity<Object> updateAccountPlan(@RequestBody UpdateAccountPlanDTO data) {

        try {
            Account updatedAccount = updateAccountPlanBusiness.execute(data);

            return ResponseErrorHandler.generateResponse("Plano da conta atualizado com sucesso.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    updatedAccount);

        } catch (FailToUpdateAccountPlanException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null,
                    FailToCreateUserException.getErrorCode());
        }

    }

}
