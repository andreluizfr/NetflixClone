package com.example.MediaAPI.Media.Exceptions;

public class MediaNotFoundException extends Exception {
    private static final int errorCode = 9;

    public MediaNotFoundException(String message) {
        super("Falha ao buscar mídia: " + message);
    }

    public static int getErrorCode() {
        return errorCode;
    }
}
