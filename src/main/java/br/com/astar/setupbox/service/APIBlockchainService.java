package br.com.astar.setupbox.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.astar.setupbox.domain.enums.StatusArquivo;
import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Ativo;
import br.com.astar.setupbox.domain.model.Evento;
import br.com.astar.setupbox.domain.repository.AtivoRepository;
import br.com.astar.setupbox.rest.RESTClient;
import br.com.astar.setupbox.rest.RESTClient.HTTPMethod;

@Service
public class APIBlockchainService {
	
	@Autowired
	private AtivoRepository ativoRepository;
	
	
	public void enviar(Ativo ativo, TipoArquivoImportacao tipoArquivo, String status) {
		
		String login = "";
		String senha = "";
		String uri = "";
		
		RESTClient client = new RESTClient(login, senha, uri);
		
		Optional<Ativo> atv = null;
		
		
		switch(tipoArquivo) {
		
		case LABORATORIO:
			client.request(uri, HTTPMethod.GET, criarEvento(ativo, tipoArquivo, status));
			atv = ativoRepository.findById(ativo.getId());
			atv.get().setStatus(StatusArquivo.ENVIADO);
			atv.get().setDataProcessamento(LocalDateTime.now());;
			ativoRepository.save(atv.get());
			break;
		case EMBALAGEM:
			client.request(uri, HTTPMethod.GET, criarEvento(ativo, tipoArquivo,status));
			atv = ativoRepository.findBySerialNumber(ativo.getSerialNumber());
			if (atv.isPresent()) {
				atv.get().setLocalizacao(tipoArquivo.name());
				atv.get().setDataProcessamento(LocalDateTime.now());
				atv.get().setStatus(StatusArquivo.ENVIADO);
				ativoRepository.save(atv.get());
			}
			break;
		case HIGIENIZACAO:
			client.request(uri, HTTPMethod.GET, criarEvento(ativo, tipoArquivo,status));
			atv = ativoRepository.findBySerialNumber(ativo.getSerialNumber());
			if (atv.isPresent()) {
				atv.get().setLocalizacao(tipoArquivo.name());
				atv.get().setDataProcessamento(LocalDateTime.now());
				atv.get().setStatus(StatusArquivo.ENVIADO);
				ativoRepository.save(atv.get());
			}
			break;
			
			
		default:
			break;
		
		}
		
	}
	
	
	private Evento criarEvento(Ativo ativo, TipoArquivoImportacao tipoArquivo, String status) {
		Evento evento = new Evento();
		evento.setSerialNumber(ativo.getSerialNumber());
		evento.setLocalizacao(tipoArquivo.name());
		evento.setDataHora(LocalDateTime.now());
		evento.setStatus(status);
		return evento;
	}
	

}
