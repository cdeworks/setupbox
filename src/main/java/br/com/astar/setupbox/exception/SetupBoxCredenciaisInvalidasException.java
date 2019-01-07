package br.com.astar.setupbox.exception;

public class SetupBoxCredenciaisInvalidasException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SetupBoxCredenciaisInvalidasException() {
		
	}
	
	public SetupBoxCredenciaisInvalidasException(final String message) {
        super(message);
    }

}
