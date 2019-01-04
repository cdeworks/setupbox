package br.com.astar.setupbox.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;

public abstract class ArquivoServiceAbstract {
	
	protected abstract List<Ativo> processar(MultipartFile file, TipoArquivoImportacao tipoArquivo) throws IOException, SetupBoxUploadArquivoInvalidoException;
	
	protected abstract void validaArquivo(MultipartFile file);
	

}
