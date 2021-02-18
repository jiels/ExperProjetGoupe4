package Donjon;

public class CommandLengthExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4024345313411457580L;

	public CommandLengthExeption() {
		System.err.println("Erreur vous devez entrer 4 actions");
		
	}

	public CommandLengthExeption(String message) {
		super(message);
		
	}

	public CommandLengthExeption(Throwable cause) {
		super(cause);
		
	}

	public CommandLengthExeption(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CommandLengthExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
