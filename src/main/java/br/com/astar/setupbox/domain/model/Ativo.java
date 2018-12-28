package br.com.astar.setupbox.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "ativos")
@XmlRootElement(name = "records")
public class Ativo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	private String tipo;
	
	
	@JsonInclude(Include.NON_NULL)
	private String modelo;
	
	
	@JsonInclude(Include.NON_NULL)
	private String fabricante;
	
	
	@JsonInclude(Include.NON_NULL)
	private String serialNumber;
	
	@JsonInclude(Include.NON_NULL)
	private String cadId;
	
	@JsonInclude(Include.NON_NULL)
	private String cmMac;
	
	@JsonInclude(Include.NON_NULL)
	private String emtaMac;
	
	@JsonInclude(Include.NON_NULL)
	private String estado;
	
	@JsonInclude(Include.NON_NULL)
	private String tipoDefeito;
	
	@JsonInclude(Include.NON_NULL)
	private String localizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlElement(name = "dut_model_desc")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@XmlElement(name = "dut_model_manu")
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	@XmlElement(name = "dut_instance_serial")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@XmlElement(name = "caid")
	public String getCadId() {
		return cadId;
	}

	public void setCadId(String cadId) {
		this.cadId = cadId;
	}

	@XmlElement(name = "mac_scan")
	public String getCmMac() {
		return cmMac;
	}

	public void setCmMac(String cmMac) {
		this.cmMac = cmMac;
	}

	public String getEmtaMac() {
		return emtaMac;
	}

	public void setEmtaMac(String emtaMac) {
		this.emtaMac = emtaMac;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoDefeito() {
		return tipoDefeito;
	}

	public void setTipoDefeito(String tipoDefeito) {
		this.tipoDefeito = tipoDefeito;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ativo other = (Ativo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ativo [id=" + id + ", tipo=" + tipo + ", modelo=" + modelo + ", fabricante=" + fabricante
				+ ", serialNumber=" + serialNumber + ", cadId=" + cadId + ", cmMac=" + cmMac + ", emtaMac=" + emtaMac
				+ ", estado=" + estado + ", tipoDefeito=" + tipoDefeito + ", localizacao=" + localizacao + "]";
	}
	
	
	

}
