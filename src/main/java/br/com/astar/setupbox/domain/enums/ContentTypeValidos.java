package br.com.astar.setupbox.domain.enums;

public enum ContentTypeValidos {
	
	XLS("application/x-tika-msoffice"),
	XLSX("application/x-tika-ooxml"),
	CSV("text/plain"),
	XML("application/xml" );

	public String tipoArquivo;
	
	ContentTypeValidos(String tipo) {
		tipoArquivo = tipo;
	}
}
