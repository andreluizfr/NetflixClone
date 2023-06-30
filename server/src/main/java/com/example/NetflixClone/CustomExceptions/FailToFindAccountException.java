package com.example.NetflixClone.CustomExceptions;

public class FailToFindAccountException extends Exception {
	private static final int errorCode = 4;

	public FailToFindAccountException(String message) {
		super("Conta não foi encontrada: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
