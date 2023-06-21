package com.example.NetflixClone.CustomExceptions;

public class FailToCreateUserException extends Exception {
	private static final int errorCode = 1;

	public FailToCreateUserException(String message) {
		super("Falha ao criar usuário: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
