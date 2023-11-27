package com.example.UserService.User.Exceptions;

public class FailToCreateUserException extends Exception {
	private static final int errorCode = 10;

	public FailToCreateUserException(String message) {
		super("Falha ao cadastrar usuário: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
