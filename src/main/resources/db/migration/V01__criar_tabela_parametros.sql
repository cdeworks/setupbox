CREATE TABLE parametros (
	
	id 			BIGINT(20) 		PRIMARY KEY AUTO_INCREMENT,
	chave		VARCHAR(50) 	NOT NULL,
	valor		VARCHAR(50)		NOT NULL,
	tipo		VARCHAR(50)
	

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO parametros (chave, valor, tipo) VALUES ("VENDOR", "fabricante", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("VENDOR MODEL", "modelo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("MODEL", "modelo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("SERIAL", "serialNumber", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("SCANNED SERIAL NUMBER", "serialNumber", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("SCANNED DECODER CA", "cadId", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("CM MAC ADDRESS", "cmMac", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("MAC ADDRESS", "cmMac", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("TEST STATUS", "estado", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("FAILURE REASON", "tipoDefeito", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("EMTA MAC ADDRESS", "emtaMac", "LABORATORIO");


INSERT INTO parametros (chave, valor, tipo) VALUES ("DEVICE TYPE", "tipo", "LABORATORIO");

INSERT INTO parametros (chave, valor, tipo) VALUES ("LOCALIZACAO", "localizacao", "LABORATORIO");