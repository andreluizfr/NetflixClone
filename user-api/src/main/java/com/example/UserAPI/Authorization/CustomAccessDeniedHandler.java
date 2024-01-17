package com.example.UserAPI.Authorization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.UserAPI.Util.ResponseHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // usado pra dar delegate.handle(request, response, accessDeniedException); caso
    // não precise ter nenhum tratamento
    // private final AccessDeniedHandlerImpl delegate = new
    // AccessDeniedHandlerImpl();

    final String errorMessage = "Acesso negado: Você não tem permissão para acessar este recurso.";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseEntity<Object> accessDeniedResponse = ResponseHandler.generateResponse(errorMessage,
                HttpStatus.UNAUTHORIZED,
                null);
        String accessDeniedResponseBody = new Gson().toJson(accessDeniedResponse.getBody());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(accessDeniedResponseBody);
        out.flush();
    }
}