package br.com.astar.setupbox.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.astar.setupbox.domain.model.DetalhesErro;

@ControllerAdvice
public class SetupBoxExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({SetupBoxUploadArquivoInvalidoException.class})
	public ResponseEntity<DetalhesErro> handleSetupBoxUploadArquivoInvalidoException(SetupBoxUploadArquivoInvalidoException ex,
											HttpServletRequest request) {
		
		String mensagem = ex.getMessage();
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo(ex.getMessage());
		erro.setTimestamp(System.currentTimeMillis());
		erro.setDetalhes(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		
		
	}
	
	
	
	
	
}
