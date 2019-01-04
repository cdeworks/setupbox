package br.com.astar.setupbox.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "de_para_bancada_rj")
public class DeParaBancadaRJ {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String codigoEstoque;
	
	private String modeloExtracao;
	
	private String fabricanteEquip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoEstoque() {
		return codigoEstoque;
	}

	public void setCodigoEstoque(String codigoEstoque) {
		this.codigoEstoque = codigoEstoque;
	}

	public String getModeloExtracao() {
		return modeloExtracao;
	}

	public void setModeloExtracao(String modeloExtracao) {
		this.modeloExtracao = modeloExtracao;
	}

	public String getFabricanteEquip() {
		return fabricanteEquip;
	}

	public void setFabricanteEquip(String fabricanteEquip) {
		this.fabricanteEquip = fabricanteEquip;
	}
	
	

}
