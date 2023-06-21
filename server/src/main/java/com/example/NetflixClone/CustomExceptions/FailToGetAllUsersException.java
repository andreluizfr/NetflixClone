package com.example.NetflixClone.CustomExceptions;

public class FailToGetAllUsersException extends Exception {
    private static final int errorCode = 1;

    public FailToGetAllUsersException(String message) {
        super("Falha ao buscar todos os usuários: " + message);
    }

    public static int getErrorCode() {
        return errorCode;
    }
}
