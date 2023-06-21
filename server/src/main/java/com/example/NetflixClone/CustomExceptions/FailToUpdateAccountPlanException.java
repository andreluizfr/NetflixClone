package com.example.NetflixClone.CustomExceptions;

public class FailToUpdateAccountPlanException extends Exception {
	private static final int errorCode = 5;

	public FailToUpdateAccountPlanException(String message) {
		super("Falha ao atualizar plano da conta: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
