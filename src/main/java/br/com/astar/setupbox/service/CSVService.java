package br.com.astar.setupbox.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

@Service
public class CSVService extends ArquivoService {
	
	private static final Logger logger = LoggerFactory.getLogger(CSVService.class);

	@Override
	protected List<Ativo> processaArquivo(MultipartFile file, TipoArquivoImportacao tipoArquivo) throws IOException {
		logger.info("Validando arquivo: " + file.getOriginalFilename());
		
		validaArquivo(file);
		
		logger.info("Processando arquivo: " + file.getOriginalFilename());
		
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		 
		try {
			InputStream initialStream = file.getInputStream();
			Reader targetReader = new InputStreamReader(initialStream);
			
			br = new BufferedReader(targetReader);
			
	        while ((linha = br.readLine()) != null) {

	            String[] ativos = linha.split(csvDivisor);

	            for (String ativo : ativos) {
					System.out.println(ativo);
				}
	            

	        }

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

	@Override
	protected void validaArquivo(MultipartFile file) {
		if (!file.getOriginalFilename().toUpperCase().contains(".CSV")) {
			throw new SetupBoxUploadArquivoInvalidoException("Formato de Arquivo Inválido!");
		}
		
	}

}
