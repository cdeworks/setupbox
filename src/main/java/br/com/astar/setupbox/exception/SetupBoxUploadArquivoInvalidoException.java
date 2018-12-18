package br.com.astar.setupbox.exception;

public class SetupBoxUploadArquivoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SetupBoxUploadArquivoInvalidoException() {
		
	}
	
	public SetupBoxUploadArquivoInvalidoException(final String message) {
        super(message);
    }

}
