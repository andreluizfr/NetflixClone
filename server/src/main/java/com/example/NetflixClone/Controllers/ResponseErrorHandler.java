package com.example.NetflixClone.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseErrorHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus httpStatus, Object responseObj) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("message", message);
        jsonMap.put("data", responseObj);

        return new ResponseEntity<Object>(jsonMap, httpStatus);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus httpStatus, Object responseObj,
            int errorCode) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("message", message);
        jsonMap.put("data", responseObj);
        jsonMap.put("error", true);
        jsonMap.put("errorCode", errorCode);

        return new ResponseEntity<Object>(jsonMap, httpStatus);
    }
}
