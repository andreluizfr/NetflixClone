package com.example.UserAPI.Util;

import java.time.format.DateTimeParseException;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> unkownError(RuntimeException e) {
        e.printStackTrace();
        return ResponseHandler.generateResponse("Erro desconhecido.", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> unkownError(IllegalArgumentException e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> unkownError(DateTimeParseException e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> concurrenceError(DataIntegrityViolationException e) {

        Throwable cause = e.getMostSpecificCause();

        if (cause instanceof PSQLException) {

            PSQLException pSQLException = (PSQLException) cause;

            if (pSQLException.getErrorCode() == 0) // unica chave com unique é email
                return ResponseHandler.generateResponse("Erro de violação de chave única",
                        HttpStatus.UNPROCESSABLE_ENTITY, null);
        }

        return ResponseHandler.generateResponse("Erro de violação de integridade de dados", HttpStatus.UNPROCESSABLE_ENTITY, null);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<Object> concurrenceError(OptimisticLockingFailureException e) {
        return ResponseHandler.generateResponse("Erro de concorrência ao tentar atualizar entidade do sistema.", HttpStatus.CONFLICT, null);
    }
}
