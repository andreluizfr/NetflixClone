package com.example.UserAPI.Account.Controller;

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

import com.example.UserAPI.Account.Business.AccountBusiness;
import com.example.UserAPI.Account.Controller.Models.UpdateAccountPlanDTO;
import com.example.UserAPI.Account.Exceptions.FailToFindAccountException;
import com.example.UserAPI.Account.Models.Account;
import com.example.UserAPI.Util.ResponseHandler;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountBusiness accountBusiness;

    @Autowired
    Gson gson;

    @HystrixCommand(
        commandKey= "/account/get?id",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getAccount(@RequestParam(name = "id", required = false) UUID id)
            throws FailToFindAccountException, IllegalArgumentException {

        Account account = accountBusiness.getAccount(id);

        return ResponseHandler.generateResponse("Conta buscada com sucesso.",
                HttpStatus.OK,
                account);
    }

    @HystrixCommand(
        commandKey= "/account/getAll",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAccount() {

        List<Account> accounts = accountBusiness.getAllAccounts();

        return ResponseHandler.generateResponse("Todas as contas buscada com sucesso.",
                HttpStatus.OK,
                accounts);
    }

    @HystrixCommand(
        commandKey= "/account/updatePlan",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @PostMapping("/updatePlan")
    public ResponseEntity<Object> updateAccountPlan(@RequestBody UpdateAccountPlanDTO data)
            throws IllegalArgumentException, FailToFindAccountException {

        Account updatedAccount = accountBusiness.updateAccountPlan(data);

        return ResponseHandler.generateResponse("Plano da conta atualizado com sucesso.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                updatedAccount);
    }

    public ResponseEntity<Object> fallbackResponse() {
        return ResponseHandler.generateResponse("Serviço está enfrentando alguns problemas.", 
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
    }

    public ResponseEntity<Object> fallbackResponse(UUID id) {
        return ResponseHandler.generateResponse("Serviço está enfrentando alguns problemas.", 
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
    }

    public ResponseEntity<Object> fallbackResponse(UpdateAccountPlanDTO data) {
        return ResponseHandler.generateResponse("Serviço está enfrentando alguns problemas.", 
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
    }

    @ExceptionHandler(FailToFindAccountException.class)
    public ResponseEntity<Object> unkownError(FailToFindAccountException e) {
        e.printStackTrace();
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
