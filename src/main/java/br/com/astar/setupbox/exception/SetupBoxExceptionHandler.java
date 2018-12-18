package br.com.astar.setupbox.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SetupBoxExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({SetupBoxUploadArquivoInvalidoException.class})
	public ResponseEntity<Object> handSetupBoxUploadArquivoInvalidoException(SetupBoxUploadArquivoInvalidoException ex,
											WebRequest request) {
		
		String mensagem = ex.getMessage();
		
		return handleExceptionInternal(ex, mensagem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
		
	}
	
	
	
}
