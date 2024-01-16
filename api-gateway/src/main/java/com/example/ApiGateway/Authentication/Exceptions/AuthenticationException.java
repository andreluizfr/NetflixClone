package com.example.ApiGateway.Authentication.Exceptions;

public class AuthenticationException extends Exception {
	private static final int errorCode = 20;

	public AuthenticationException() {
		super("Email ou senha incorreta.");
	}

	public AuthenticationException(String message) {
		super("Email ou senha incorreta: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
