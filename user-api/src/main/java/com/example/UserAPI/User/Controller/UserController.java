package com.example.UserAPI.User.Controller;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserAPI.User.Exceptions.FailToFindUserException;
import com.example.UserAPI.User.Business.UserBusiness;
import com.example.UserAPI.User.Controller.Models.CreateUserDTO;
import com.example.UserAPI.User.Models.User;
import com.example.UserAPI.Util.ResponseHandler;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserBusiness userBusiness;

    @Autowired
    Gson gson;

    @HystrixCommand(
        commandKey= "/user?id",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getUser(@RequestParam(name = "id", required = true) UUID id)
            throws IllegalArgumentException, FailToFindUserException {

        User user = userBusiness.getUser(id);

        return ResponseHandler.generateResponse("Usuário buscado com sucesso.",
                HttpStatus.OK,
                user);
    }

    @HystrixCommand(
        commandKey= "/user/getAll",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUser() {

        List<User> users = userBusiness.getAllUsers();

        return ResponseHandler.generateResponse("Usuários buscados com sucesso.",
                HttpStatus.OK, users);
    }

    @HystrixCommand(
        commandKey= "/user/create",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO data)
            throws IllegalAccessException, DataIntegrityViolationException, DateTimeParseException {

        User newUser = userBusiness.createUser(data);

        return ResponseHandler.generateResponse("Conta criada com sucesso.", HttpStatus.CREATED,
                newUser);
    }

    @HystrixCommand(
        commandKey= "/user/fetch",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchUser(@RequestHeader("X-Logged-In-User") String userEmail)
            throws FailToFindUserException, IllegalArgumentException {

        User user = userBusiness.fetchUser(userEmail);

        return ResponseHandler.generateResponse("Dados de usuário buscados com sucesso.", HttpStatus.OK,
                user);
    }

    @HystrixCommand(
        commandKey= "/user/checkEmail",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @GetMapping("/checkEmail")
    public ResponseEntity<Object> checkEmail(@RequestHeader("X-Logged-In-User") String email)
            throws FailToFindUserException, IllegalArgumentException {

        boolean check = userBusiness.checkEmail(email);

        return ResponseHandler.generateResponse("Email checado com sucesso.", HttpStatus.OK,
                check);
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

    public ResponseEntity<Object> fallbackResponse(CreateUserDTO data) {
        return ResponseHandler.generateResponse("Serviço está enfrentando alguns problemas.", 
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
    }

    public ResponseEntity<Object> fallbackResponse(String userEmail) {
        return ResponseHandler.generateResponse("Serviço está enfrentando alguns problemas.", 
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
    }


    @ExceptionHandler(FailToFindUserException.class)
    public ResponseEntity<Object> unkownError(FailToFindUserException e) {
        System.out.println(e.getMessage());
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
