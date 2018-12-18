package br.com.astar.setupbox.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.domain.model.Parametro;
import br.com.astar.setupbox.domain.repository.ParametroRepository;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

@Service
public class LaboratorioService {
	
	private static final Logger logger = LoggerFactory.getLogger(LaboratorioService.class);
	
	
	@Autowired
	private ParametroRepository parametroRepository;
	
	public boolean validaContentType(MultipartFile file)  {
		if (getContentType(file) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void processaArquivo(MultipartFile file) throws IOException {
		
		List<Parametro> headers = parametroRepository.findAll();
		
		ContentTypeValidos tipoArquivo = getContentType(file);
		
		if (tipoArquivo.equals(ContentTypeValidos.XLS) || tipoArquivo.equals(ContentTypeValidos.XLSX) ) {
			processaArquivoExcel(file);
		}
		
//		for (Parametro parametro : headers) {
//			logger.info("Chave: " + parametro.getChave() + " Valor: " + parametro.getValor());
//		}
//		
	}
	
	
	private void processaArquivoExcel(MultipartFile file) throws IOException {
		logger.info("Processando arquivo Excel: " + file.getOriginalFilename());
		
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		 
		 XSSFSheet sheet = workbook.getSheetAt(0);
		 
		 Iterator<Row> rowIterator = sheet.iterator();
		 
		 while (rowIterator.hasNext()) {
			 Row row = rowIterator.next();
			 Iterator<Cell> cellIterator = row.cellIterator();
			 
			 while (cellIterator.hasNext()) {
				 Cell cell = cellIterator.next();
				 logger.info(cell.getStringCellValue());
			 }
		 }
		
		
	}

	private ContentTypeValidos getContentType(MultipartFile file) {
		File f = new File("d:/testes/laboratorio-" + file.getOriginalFilename());
		Tika tika = new Tika();
		String tipoArquivo = null;
		try {
			file.transferTo(f);
			tipoArquivo = tika.detect(f);
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage(), e.getCause());
			throw new SetupBoxUploadArquivoInvalidoException("Ocorreu um erro ao subir o arquivo!");
		}
		
		logger.info("Tipo Arquivo: "+ tipoArquivo);
		
		for (ContentTypeValidos contentType : ContentTypeValidos.values()) {
			if (contentType.tipoArquivo.equals(tipoArquivo)) {
				return contentType;
			}
		}
		logger.info("Tipo de arquivo inválido!");
		throw new SetupBoxUploadArquivoInvalidoException("Tipo de arquivo inválido!");
		
	}

	
	
	
}
