package com.example.NetflixClone.CustomExceptions;

public class FailToGetPaymentMethodsException extends Exception{
    private static final int errorCode = 8;

	public  FailToGetPaymentMethodsException(String message) {
		super("Falha ao buscar métodos de pagamento: " + message);
	}

	public static int getErrorCode() {
		return errorCode;
	}
}
