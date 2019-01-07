package br.com.astar.setupbox.resouces;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.exception.SetupBoxCredenciaisInvalidasException;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;
import br.com.astar.setupbox.service.CSVService;
import br.com.astar.setupbox.service.ExcelService;
import br.com.astar.setupbox.service.LaboratorioService;
import br.com.astar.setupbox.service.XMLService;
import br.com.astar.setupbox.util.FileUtil;

@RestController
@RequestMapping("laboratorios")
public class LaboratorioResource {
	
	private static final Logger logger = LoggerFactory.getLogger(LaboratorioResource.class);
	
	@Autowired
	private LaboratorioService laboratorioService;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private CSVService csvService;
	
	@Autowired
	private XMLService xmlService;
	
	@PostMapping
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		 if (!request.isUserInRole("ROLE_LABORATORIO")) {
            throw new SetupBoxCredenciaisInvalidasException("Credenciais sem autorização para enviar este tipo de arquivo.");
        }
		validaArquivoLaboratorio(file);
		
		ContentTypeValidos tipoArquivo = FileUtil.getContentType(file);
		
		laboratorioService.processaArquivo(file, tipoArquivo);
		
		return ResponseEntity.ok("Arquivo recebido com sucesso.");
		
	}
	
	@GetMapping
	public List<Ativo> listar() throws IOException {
		return null;
	}
	
	private void validaArquivoLaboratorio(MultipartFile file) {
		logger.info("Validando arquivo: " + file.getOriginalFilename());

		String nomeArquivo = file.getOriginalFilename();

		if (nomeArquivo.toUpperCase().trim().contains("banc-".toUpperCase())) {
			excelService.validaArquivo(file);
		} else if (nomeArquivo.toUpperCase().trim().contains("cmtp-".toUpperCase()) ||
				   nomeArquivo.toUpperCase().trim().contains("stbtp-".toUpperCase())) {
			csvService.validaArquivo(file);
		} else if (nomeArquivo.toUpperCase().trim().contains("titan-".toUpperCase())) {
			xmlService.validaArquivo(file);
		} else {
			throw new SetupBoxUploadArquivoInvalidoException("Arquivo Inválido!");
		}
		
	}

}
