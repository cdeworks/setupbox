package br.com.astar.setupbox.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.domain.model.Parametro;
import br.com.astar.setupbox.domain.repository.ParametroRepository;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

@Service
public class ExcelService extends ArquivoServiceAbstract {

	private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);
	
	@Autowired
	private ParametroRepository parametroRepository;
	
	@Override
	public List<Ativo> importaArquivo(MultipartFile file, TipoArquivoImportacao tipoArquivo) throws IOException {
		logger.info("Validando arquivo: " + file.getOriginalFilename());
		
		validaArquivo(file);
		
		logger.info("Processando arquivo: " + file.getOriginalFilename());
		
		List<Ativo> ativos = new ArrayList<>();
		
		Workbook workbook;
		Sheet sheet;
		
		if(getContentType(file).equals(ContentTypeValidos.XLS)) {
			workbook = new HSSFWorkbook(file.getInputStream());
		} else {
			workbook = new XSSFWorkbook(file.getInputStream());
		}
		 
		sheet = workbook.getSheetAt(0);
		 
		Iterator<Row> rowIterator = sheet.iterator();
		
		// Pega o header dos campos
		Map<Integer, String> colunasParaImportacao = getColunasExcelParaImportacao(rowIterator, tipoArquivo);
		
		// zera o iterator
		rowIterator = sheet.iterator();
		
		while (rowIterator.hasNext()) {
			
			Row row = rowIterator.next();
			
			// Se for a linha 0 ele ignora porque é a linha dos headers
			if (row.getRowNum() == 0 ) {
				continue;
			}
				
			Ativo ativo = preencheAtivo(row, colunasParaImportacao);
			ativo.setLocalizacao(tipoArquivo.name());
			ativos.add(ativo);
			
		}
		 
		 workbook.close();
		 
		 logger.info("Total de linhas do XML(X) importados: " + ativos.size());
		 
		 return ativos;
	}
	
	protected void validaArquivo(MultipartFile file) {
		if (file.getOriginalFilename().toUpperCase().contains(".XLSB") 
				|| file.getOriginalFilename().toUpperCase().contains(".XLSC")
				|| file.getOriginalFilename().toUpperCase().contains(".XLSW")) {
			throw new SetupBoxUploadArquivoInvalidoException("Formato de Arquivo Inválido!");
		}
	}

	private Ativo preencheAtivo(Row linha, Map<Integer, String> colunas)  {
		try {
			Class<?> ativoClass = Ativo.class;
			Object instanciaAtivo;
			instanciaAtivo = ativoClass.getConstructor(new Class[] {}).newInstance();
		
		
			Field[] camposHeader = Ativo.class.getDeclaredFields();
			
			colunas.entrySet().stream()
				.forEach(x -> {
					for (Field field : camposHeader) {
						if (x.getValue().toUpperCase().trim().equals(field.getName().toUpperCase().trim())) {
								
								try {
									Field atributo = ativoClass.getDeclaredField(field.getName());
									atributo.setAccessible(true);
									if (linha.getCell(x.getKey()).getCellType().equals(CellType.STRING)) {
										String valor = linha.getCell(x.getKey()).getStringCellValue();
										atributo.set(instanciaAtivo, valor != null ? valor.toString() : null);
									} else if(linha.getCell(x.getKey()).getCellType().equals(CellType.NUMERIC)) {
										Double valor = linha.getCell(x.getKey()).getNumericCellValue();
										atributo.set(instanciaAtivo, valor != null ? valor.toString() : null);
									} else {
										atributo.set(instanciaAtivo, null);
									}
								} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
									logger.error("Erro: " + e.getMessage());
								}
							} 
								
						}
				});
			
			
			return (Ativo) instanciaAtivo;
		
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			logger.error("Erro: " + e1.getMessage());
			return null;
		}
		
	}
	
	private Map<Integer, String> getColunasExcelParaImportacao(Iterator<Row> rowIterator, TipoArquivoImportacao tipoArquivo) {
		List<Parametro> headers = parametroRepository.findByTipo(tipoArquivo);
		
		Map<Integer,String> colunas = new HashMap<>();
		
		 Row row = rowIterator.next();
		 
		 Iterator<Cell> cellIterator = row.cellIterator();
		 while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			for (Parametro parametro : headers) {
				if (parametro.getChave().toUpperCase().equals(cell.getStringCellValue().toUpperCase())) {
					colunas.put(cell.getColumnIndex(), parametro.getValor());
				}
			}
			 
		 }
		
		return colunas;
	}

}
