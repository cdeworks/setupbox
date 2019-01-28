package br.com.astar.setupbox.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

@Service
public class EmbalagemService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmbalagemService.class);
	
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private APIBlockchainService apiService;
	
	
	@Async("fileExecutor")
	public void processaArquivo(MultipartFile file, ContentTypeValidos tipoArquivo) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		logger.info("Iniciando serviço....passando pelo motor de importação");
		
		String status = file.getOriginalFilename().toUpperCase().trim().contains("cos-".toUpperCase()) ? "COSMETICO" : "ELETRICO";
		
		
		List<Ativo> ativos = new ArrayList<>();
		
		
		if (tipoArquivo.equals(ContentTypeValidos.XLS) || tipoArquivo.equals(ContentTypeValidos.XLSX)) {
			ativos = excelService.processar(file, TipoArquivoImportacao.EMBALAGEM);
		} else {
			throw new SetupBoxUploadArquivoInvalidoException("Formato de Arquivo Inválido!");
		}
		
		logger.info("Arquivo importado.....");
		
		logger.info("Buscando de para de defeitos e salvando ativos...");
		
		for (Ativo ativo : ativos) {
			//TODO - ENVIA PARA API BLOCKCHAIN
			apiService.enviar(ativo, TipoArquivoImportacao.EMBALAGEM, status);
		}

		
	}

	
}
