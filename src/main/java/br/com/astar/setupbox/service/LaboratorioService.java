package br.com.astar.setupbox.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;

@Service
public class LaboratorioService {
	
	private static final Logger logger = LoggerFactory.getLogger(LaboratorioService.class);
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private CSVService csvService;
	
	@Autowired
	private XMLService xmlService;
	
	public void processaArquivo(MultipartFile file) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ContentTypeValidos tipoArquivo = excelService.getContentType(file);
		
		List<Ativo> ativos = new ArrayList<>();
		
		if (tipoArquivo.equals(ContentTypeValidos.XLS) || tipoArquivo.equals(ContentTypeValidos.XLSX)) {
			ativos = excelService.processaArquivo(file, TipoArquivoImportacao.LABORATORIO);
		} else if (tipoArquivo.equals(ContentTypeValidos.CSV)) {
			ativos = csvService.processaArquivo(file, TipoArquivoImportacao.LABORATORIO);
		} else {
			ativos = xmlService.processaArquivo(file, TipoArquivoImportacao.LABORATORIO);
		}
	}
		
	
}
