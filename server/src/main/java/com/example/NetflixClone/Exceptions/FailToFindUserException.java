package com.example.NetflixClone.Exceptions;

public class FailToFindUserException extends Exception {
    private static final int errorCode = 2;

    public FailToFindUserException(String message) {
        super("Falha ao buscar usuário: " + message);
    }

    public static int getErrorCode() {
        return errorCode;
    }
}
