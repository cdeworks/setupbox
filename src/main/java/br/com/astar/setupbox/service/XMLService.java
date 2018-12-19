package br.com.astar.setupbox.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.domain.repository.ParametroRepository;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

@Service
public class XMLService extends ArquivoServiceAbstract{
	
	private static final Logger logger = LoggerFactory.getLogger(XMLService.class);
	
	@Autowired
	private ParametroRepository parametroRepository;

	@Override
	protected List<Ativo> processaArquivo(MultipartFile file, TipoArquivoImportacao tipoArquivo) throws IOException {
		logger.info("Validando arquivo: " + file.getOriginalFilename());
		
		validaArquivo(file);
		
		logger.info("Processando arquivo: " + file.getOriginalFilename());
		
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file.getInputStream());
			
			//Passo 1: obter o elemento raiz
		    Element raiz = doc.getDocumentElement();
		    System.out.println("O elemento raiz é: " + raiz.getNodeName());
			
		    NodeList records = raiz.getChildNodes();
		    
		    for (int i=0; i< records.getLength(); i++) {
		    	System.out.println(records.item(i).getNodeName());
		    	NodeList campos = records.item(i).getChildNodes();
		    	for (int j=0;j< campos.getLength(); j++) {
		    		System.out.println(campos.item(j).getNodeValue());
		    	}
		    	break;
		    }
			
		    } catch (Exception e) {
		    }
		
		
		return null;
	}

	@Override
	protected void validaArquivo(MultipartFile file) {
		if (!file.getOriginalFilename().toUpperCase().contains(".XML")) {
			throw new SetupBoxUploadArquivoInvalidoException("Formato de Arquivo Inválido!");
		}
		
	}

}
