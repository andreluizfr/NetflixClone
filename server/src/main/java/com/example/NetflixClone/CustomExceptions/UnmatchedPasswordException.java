package com.example.NetflixClone.CustomExceptions;

public class UnmatchedPasswordException extends Exception {
	private static final int errorCode = 9;

	public UnmatchedPasswordException(String message) {
		super("Falha ao comparar senhas: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
