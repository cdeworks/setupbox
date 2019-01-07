package br.com.astar.setupbox.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Evento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String serialNumber;
	
	private String localizacao;
	
	private String status;
	
	private LocalDateTime dataHora;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "Evento [serialNumber=" + serialNumber + ", localizacao=" + localizacao + ", status=" + status
				+ ", dataHora=" + dataHora + "]";
	}

	
	
	
	
	
}
