package com.example.ApiGateway.Util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus httpStatus, Object responseObj) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("message", message);
        jsonMap.put("data", responseObj);

        return ResponseEntity.status(httpStatus).body(jsonMap);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus httpStatus, Object responseObj,
            int errorCode) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("message", message);
        jsonMap.put("data", responseObj);
        jsonMap.put("errorCode", errorCode);

        return ResponseEntity.status(httpStatus).body(jsonMap);
    }

    public static String generateBody(String message, Object responseObj) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("message", message);
        jsonMap.put("data", responseObj);

        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonMap, HashMap.class);

        return jsonString;
    }
}
