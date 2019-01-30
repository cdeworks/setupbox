package br.com.astar.setupbox.resouces;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.exception.SetupBoxCredenciaisInvalidasException;
import br.com.astar.setupbox.service.ReparadoraService;
import br.com.astar.setupbox.util.FileUtil;

@RestController
@RequestMapping("reparadora")
public class ReparadoraResource {
	
	private static final Logger logger = LoggerFactory.getLogger(ReparadoraResource.class);
	
	
	@Autowired
	private ReparadoraService reparadoraService;
	
	
	@PostMapping
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		logger.info("Importando arquivo: " + file.getOriginalFilename());
		
		 if (!request.isUserInRole("ROLE_REPARADORA")) {
            throw new SetupBoxCredenciaisInvalidasException("Credenciais sem autorização para enviar este tipo de arquivo.");
        }
		 
		ContentTypeValidos tipoArquivo = FileUtil.getContentType(file);
		
		reparadoraService.processaArquivo(file, tipoArquivo);
		
		return ResponseEntity.ok("Arquivo recebido com sucesso.");
		
	}
	
	

}
