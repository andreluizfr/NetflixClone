package com.example.MediaService.Media.Exceptions;

public class PreviewMediaNotFoundException extends Exception {
    private static final int errorCode = 9;

    public PreviewMediaNotFoundException(String message) {
        super("Falha ao buscar Preview: " + message);
    }

    public static int getErrorCode() {
        return errorCode;
    }
}
