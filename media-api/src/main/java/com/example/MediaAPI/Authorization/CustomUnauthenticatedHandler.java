package com.example.MediaAPI.Authorization;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.MediaAPI.Util.ResponseHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomUnauthenticatedHandler implements AuthenticationEntryPoint {

    final String errorMessage = "Acesso negado: Você não está autenticado.";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        ResponseEntity<Object> customUnauthenticatedResponse = ResponseHandler.generateResponse(errorMessage,
                HttpStatus.FORBIDDEN,
                null);
        String customUnauthenticatedResponseBody = new Gson().toJson(customUnauthenticatedResponse.getBody());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(customUnauthenticatedResponseBody);
        out.flush();
    }
}
