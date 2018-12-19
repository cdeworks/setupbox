CREATE TABLE parametros (
	
	id 			BIGINT(20) 		PRIMARY KEY AUTO_INCREMENT,
	chave		VARCHAR(50) 	NOT NULL,
	valor		VARCHAR(50)		NOT NULL,
	tipo		VARCHAR(50)
	

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO parametros (chave, valor, tipo) VALUES ("TIPO", "tipo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("MODEL", "modelo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("FABRICANTE", "fabricante", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("SERIAL", "serialNumber", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("CADID", "cadId", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("CMAC", "cmMac", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("EMTAMAC", "emtaMac", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("ESTADO", "estado", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("TIPODEFEITO", "tipoDefeito", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("LOCALIZACAO", "localizacao", "LABORATORIO");