package com.example.NetflixClone.CustomExceptions;

public class FailToGetAccountException extends Exception {
	private static final int errorCode = 4;

	public FailToGetAccountException(String message) {
		super("Conta não foi encontrada: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
