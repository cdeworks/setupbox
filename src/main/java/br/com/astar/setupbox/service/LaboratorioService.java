package br.com.astar.setupbox.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.astar.setupbox.domain.enums.ContentTypeValidos;
import br.com.astar.setupbox.domain.enums.StatusArquivo;
import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.domain.model.DeParaGigasBancada;
import br.com.astar.setupbox.domain.repository.AtivoRepository;
import br.com.astar.setupbox.domain.repository.GigasBancadaRepository;
import br.com.astar.setupbox.exception.SetupBoxUploadArquivoInvalidoException;
import br.com.astar.setupbox.util.FileUtil;

@Service
public class LaboratorioService {
	
	private static final Logger logger = LoggerFactory.getLogger(LaboratorioService.class);
	
	private static final String STBTP = "STBTP";

	private static final String CMTP = "CMTP";
	
	private static final String BANC = "BANC";
	
	private static final String TITAN = "S3";
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private CSVService csvService;
	
	@Autowired
	private XMLService xmlService;
	
	@Autowired
	private AtivoRepository ativoRepository;
	
	@Autowired
	private GigasBancadaRepository gigaBancadaRepository;
	
	@Async("fileExecutor")
	public void processaArquivo(MultipartFile file, ContentTypeValidos tipoArquivo) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		logger.info("Iniciando serviço....passando pelo motor de importação");
		
		List<Ativo> ativos = new ArrayList<>();
		
		String gigas = getGigasType(file.getOriginalFilename());
		
		if (tipoArquivo.equals(ContentTypeValidos.XLS) || tipoArquivo.equals(ContentTypeValidos.XLSX)) {
			ativos = excelService.processar(file, TipoArquivoImportacao.LABORATORIO);
		} else if (tipoArquivo.equals(ContentTypeValidos.CSV)) {
			ativos = csvService.processar(file, TipoArquivoImportacao.LABORATORIO);
		} else if (tipoArquivo.equals(ContentTypeValidos.XML)){
			ativos = xmlService.processar(file, TipoArquivoImportacao.LABORATORIO);
		} else {
			throw new SetupBoxUploadArquivoInvalidoException("Formato de Arquivo Inválido!");
		}
		
		logger.info("Arquivo importado.....");
		
		logger.info("Buscando de para de defeitos e salvando ativos...");
		
		for (Ativo ativo : ativos) {
			ativo.setLocalizacao(TipoArquivoImportacao.LABORATORIO.name());
			ativo.setStatus(StatusArquivo.RECEBIDO);
			ativo.setDataImportacao(LocalDate.now());
			preencheDefeito(ativo, gigas);
			ativoRepository.save(ativo);
			
		}

		
		
	}

	private void preencheDefeito(Ativo ativo, String gigas) {
		if (ativo.getTipoDefeito() != null && ! "".equals(ativo.getTipoDefeito())) {
			DeParaGigasBancada gb = gigaBancadaRepository.findByDiagnosticoAndGigas(ativo.getTipoDefeito(), gigas);
			if (gb != null && gb.getDiagnostico() != null) {
				ativo.setTipoDefeito(gb.getTipo());
			} else {
				ativo.setTipoDefeito(null);
			}
		}
		
	}
	
	private String getGigasType(String originalFilename) {
		if (originalFilename.toUpperCase().trim().contains(CMTP)) {
			return CMTP;
		} else if (originalFilename.toUpperCase().trim().contains(STBTP)) {
			return STBTP;
		} else if (originalFilename.toUpperCase().trim().contains(BANC)) {
			return BANC;
		} else {
			return TITAN;
		}
	}
		
	
}
