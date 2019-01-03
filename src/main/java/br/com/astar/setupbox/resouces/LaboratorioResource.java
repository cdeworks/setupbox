package br.com.astar.setupbox.resouces;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.service.LaboratorioService;

@RestController
@RequestMapping("laboratorios")
public class LaboratorioResource {
	
	private static final Logger logger = LoggerFactory.getLogger(LaboratorioResource.class);
	
	@Autowired
	private LaboratorioService laboratorioService;
	
	
	@PostMapping
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		
		laboratorioService.processaArquivo(file);
		
		return ResponseEntity.ok("Arquivo recebido com sucesso.");
		
	}
	
	
	@GetMapping
	public List<Ativo> listar() throws IOException {
		return null;
	}

}
