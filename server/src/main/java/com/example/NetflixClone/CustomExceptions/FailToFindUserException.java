package com.example.NetflixClone.CustomExceptions;

public class FailToFindUserException extends Exception {
    private static final int errorCode = 2;

    public FailToFindUserException(String message) {
        super("Falha ao bsucar usuário: " + message);
    }

    public static int getErrorCode() {
        return errorCode;
    }
}
