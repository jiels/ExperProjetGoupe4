package Donjon;

public class CommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4024345313411457580L;

	public CommandException() {
		System.err.println("Erreur vous devez entrer 4 actions");
		
	}

	public CommandException(String message) {
		super(message);
		
	}

	public CommandException(Throwable cause) {
		super(cause);
		
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CommandException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
