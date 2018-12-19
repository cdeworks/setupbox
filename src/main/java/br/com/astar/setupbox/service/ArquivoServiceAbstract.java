package br.com.astar.setupbox.service;

import java.io.IOException;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

public abstract class ArquivoServiceAbstract {
	
	protected abstract List<Ativo> processaArquivo(MultipartFile file, TipoArquivoImportacao tipoArquivo) throws IOException;
	
	protected abstract void validaArquivo(MultipartFile file);
	
	public ContentTypeValidos getContentType(MultipartFile file) {
		Tika tika = new Tika();
		String tipoArquivo = null;
		try {
			tika.detect(file.getInputStream());
			tipoArquivo = tika.detect(file.getInputStream()); 
		} catch (IllegalStateException | IOException e) {
			throw new SetupBoxUploadArquivoInvalidoException("Ocorreu um erro ao subir o arquivo!");
		}
		
		
		for (ContentTypeValidos contentType : ContentTypeValidos.values()) {
			if (contentType.tipoArquivo.equals(tipoArquivo)) {
				return contentType;
			}
		}
		throw new SetupBoxUploadArquivoInvalidoException("Tipo de arquivo inválido!");
		
	}

}
