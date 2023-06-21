package com.example.NetflixClone.CustomExceptions;

public class FailToGetUserException extends Exception {
    private static final int errorCode = 2;

    public FailToGetUserException(String message) {
        super("Falha ao bsucar usuário: " + message);
    }

    public static int getErrorCode() {
        return errorCode;
    }
}
