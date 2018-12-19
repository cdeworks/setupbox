package br.com.astar.setupbox.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;

@Entity
@Table(name = "parametros")
public class Parametro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String chave;
	
	private String valor;
	
	@Enumerated(EnumType.STRING)
	private TipoArquivoImportacao tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TipoArquivoImportacao getTipoArquivo() {
		return tipo;
	}

	public void setTipo(TipoArquivoImportacao tipo) {
		this.tipo = tipo;
	}
	
	

}
