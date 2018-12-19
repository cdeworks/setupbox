package br.com.astar.setupbox.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.domain.model.Parametro;
import br.com.astar.setupbox.domain.repository.ParametroRepository;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

@Service
public class CSVService extends ArquivoServiceAbstract {
	
	private static final Logger logger = LoggerFactory.getLogger(CSVService.class);
	
	@Autowired
	private ParametroRepository parametroRepository;

	@Override
	protected List<Ativo> processaArquivo(MultipartFile file, TipoArquivoImportacao tipoArquivo) throws IOException {
		logger.info("Validando arquivo: " + file.getOriginalFilename());
		
		validaArquivo(file);
		
		logger.info("Processando arquivo: " + file.getOriginalFilename());
		
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = "\",\"";
		 
		List<Ativo> ativos = new ArrayList<>();
		
		try {
			InputStream initialStream = file.getInputStream();
			Reader targetReader = new InputStreamReader(initialStream);
			
			br = new BufferedReader(targetReader);
			int totalLinhas = 0;
			Map<Integer, String> colunasParaImportacao = new HashMap<>();
	        while ((linha = br.readLine()) != null) {
	        	
	        	if (totalLinhas == 0) {
	        		colunasParaImportacao = getColunasParaImportacao(linha, tipoArquivo);
	        		totalLinhas++;
	        		continue;
	        	}
	        	
	        	totalLinhas++;

	            
	            Ativo ativo = preencheAtivo(linha.split(csvDivisor), colunasParaImportacao);
	            
	            ativo.setLocalizacao(tipoArquivo.name());
				
				ativos.add(ativo);

	            for (Ativo a : ativos) {
					System.out.println(a);
				}
	            

	        }
	        System.out.println("Total de linhas importadas: " + totalLinhas);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		
		
		return null;
	}

	private Ativo preencheAtivo(String[] linha, Map<Integer, String> colunas) {
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
									String valor = linha[x.getKey()].replaceAll("\"", "");
									atributo.set(instanciaAtivo, valor != null ? valor.toString() : null);
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

	private Map<Integer, String> getColunasParaImportacao(String linha, TipoArquivoImportacao tipoArquivo) {
		List<Parametro> headers = parametroRepository.findByTipo(tipoArquivo);
		
		Map<Integer,String> colunas = new HashMap<>();

		String[] cols = linha.split(",");
		
		for (int i=0; i< cols.length; i++) {
			for (Parametro parametro : headers) {
				if (parametro.getChave().toUpperCase().equals(cols[i].replaceAll("\"", "").toUpperCase().trim())) {
					colunas.put(i, parametro.getValor());
				}
			}
			
		}
		
		return colunas;
	}

	@Override
	protected void validaArquivo(MultipartFile file) {
		if (!file.getOriginalFilename().toUpperCase().contains(".CSV")) {
			throw new SetupBoxUploadArquivoInvalidoException("Formato de Arquivo Inválido!");
		}
		
	}

}
