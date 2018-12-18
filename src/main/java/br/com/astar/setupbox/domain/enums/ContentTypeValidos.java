package br.com.astar.setupbox.domain.enums;

public enum ContentTypeValidos {
	
	XLS("application/vnd.ms-excel"),
	XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	CSV("text/csv"),
	XML("application/xml" );

	public String tipoArquivo;
	
	ContentTypeValidos(String tipo) {
		tipoArquivo = tipo;
	}
}
