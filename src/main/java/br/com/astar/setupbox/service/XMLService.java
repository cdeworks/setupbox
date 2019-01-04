package br.com.astar.setupbox.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.domain.model.Root;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

@Service
public class XMLService extends ArquivoServiceAbstract{
	
	private static final Logger logger = LoggerFactory.getLogger(XMLService.class);
	
	
	@Override
	protected List<Ativo> processar(MultipartFile file, TipoArquivoImportacao tipoArquivo) throws IOException {
		
		logger.info("Processando arquivo: " + file.getOriginalFilename());
		
		List<Ativo> ativos = new ArrayList<>();
		
		try {
			Root root = new Root();
			JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        root = (Root) unmarshaller.unmarshal(file.getInputStream());
	        System.out.println(root);
	        
	        ativos = root.getAtivos();
	        
	        
			logger.info("Total de linhas do XML importados: " + ativos.size());
			
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    	e.printStackTrace();
	    }
		
		if (ativos == null) {
        	ativos = new ArrayList<>();
        }
		
		return ativos;
		
	}

	@Override
	public void validaArquivo(MultipartFile file) {
		logger.info("Validando arquivo: " + file.getOriginalFilename());
		if (!file.getOriginalFilename().toUpperCase().contains(".XML")) {
			throw new SetupBoxUploadArquivoInvalidoException("Formato de Arquivo Inválido!");
		}
		
	}

}
