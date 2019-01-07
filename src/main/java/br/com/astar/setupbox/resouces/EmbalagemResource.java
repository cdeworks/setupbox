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
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;
import br.com.astar.setupbox.service.EmbalagemService;
import br.com.astar.setupbox.service.ExcelService;
import br.com.astar.setupbox.util.FileUtil;

@RestController
@RequestMapping("embalagens")
public class EmbalagemResource {
	
	private static final Logger logger = LoggerFactory.getLogger(EmbalagemResource.class);
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private EmbalagemService embalagemService;
	
	
	@PostMapping
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		logger.info("Importando arquivo: " + file.getOriginalFilename());
		
		 if (!request.isUserInRole("ROLE_EMBALAGEM")) {
            throw new SetupBoxCredenciaisInvalidasException("Credenciais sem autorização para enviar este tipo de arquivo.");
        }
		 
		 validaArquivoEmbalagem(file);
		
		ContentTypeValidos tipoArquivo = FileUtil.getContentType(file);
		
		embalagemService.processaArquivo(file, tipoArquivo);
		
		return ResponseEntity.ok("Arquivo recebido com sucesso.");
		
	}
	
	private void validaArquivoEmbalagem(MultipartFile file) {
		logger.info("Validando arquivo: " + file.getOriginalFilename());

		String nomeArquivo = file.getOriginalFilename();

		if (nomeArquivo.toUpperCase().trim().contains("cos-".toUpperCase()) ||
			nomeArquivo.toUpperCase().trim().contains("ele-".toUpperCase())) {
			excelService.validaArquivo(file);
		} else {
			throw new SetupBoxUploadArquivoInvalidoException("Arquivo Inválido!");
		}
		
	}

}
