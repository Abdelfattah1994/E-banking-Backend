package ebanking_backend.com.rabou.exceptions;

public class BalanceNotSufficientException extends Exception {

	public BalanceNotSufficientException(String message) {
		super(message);
	}
}
