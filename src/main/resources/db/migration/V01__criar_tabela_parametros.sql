CREATE TABLE parametros (
	
	id 			BIGINT(20) 		PRIMARY KEY AUTO_INCREMENT,
	chave		VARCHAR(50) 	NOT NULL,
	valor		VARCHAR(50)		NOT NULL,
	tipo		VARCHAR(50)
	

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO parametros (chave, valor) VALUES ("TIPO", "tipo");
INSERT INTO parametros (chave, valor) VALUES ("MODEL", "modelo");
INSERT INTO parametros (chave, valor) VALUES ("FABRICANTE", "fabricante");
INSERT INTO parametros (chave, valor) VALUES ("SERIAL", "serialNumber");
INSERT INTO parametros (chave, valor) VALUES ("CADID", "cadId");
INSERT INTO parametros (chave, valor) VALUES ("CMAC", "cmMac");
INSERT INTO parametros (chave, valor) VALUES ("EMTAMAC", "emtaMac");
INSERT INTO parametros (chave, valor) VALUES ("ESTADO", "estado");
INSERT INTO parametros (chave, valor) VALUES ("TIPODEFEITO", "tipoDefeito");
INSERT INTO parametros (chave, valor) VALUES ("LOCALIZACAO", "localizacao");