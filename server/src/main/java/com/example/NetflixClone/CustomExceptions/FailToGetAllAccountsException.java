package com.example.NetflixClone.CustomExceptions;

public class FailToGetAllAccountsException extends Exception {
    private static final int errorCode = 6;

	public FailToGetAllAccountsException(String message) {
		super("Falha ao buscar todas as contas: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
