CREATE TABLE parametros (
	
	id 			BIGINT(20) 		PRIMARY KEY AUTO_INCREMENT,
	chave		VARCHAR(50) 	NOT NULL,
	valor		VARCHAR(50)		NOT NULL,
	tipo		VARCHAR(50)
	

) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- de para NETBR-STBTP
INSERT INTO parametros (chave, valor, tipo) VALUES ("DEVICE TYPE", "tipo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("VENDOR MODEL", "modelo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("VENDOR", "fabricante", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("SERIAL", "serialNumber", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("SCANNED DECODER CA", "cadId", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("CM MAC ADDRESS", "cmMac", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("FAILURE REASON", "tipoDefeito", "LABORATORIO");


-- de para NETBR-CMTP
INSERT INTO parametros (chave, valor, tipo) VALUES ("MODEL", "modelo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("MAC ADDRESS", "cmMac", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("EMTA MAC ADDRESS", "emtaMac", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("TEST STATUS", "estado", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("REASON", "tipoDefeito", "LABORATORIO");
-- INSERT INTO parametros (chave, valor, tipo) VALUES ("SCANNED SERIAL NUMBER", "serialNumber", "LABORATORIO");


-- de para TestCycleExport
INSERT INTO parametros (chave, valor, tipo) VALUES ("DUT_MODEL_DESC", "modelo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("DUT_MODEL_MANU", "fabricante", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("DUT_INSTANCE_SERIAL", "serialNumber", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("CAID", "cadId", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("RESULT", "estado", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("FAILURES", "tipoDefeito", "LABORATORIO");


-- de para Bancada RJ 24.08
INSERT INTO parametros (chave, valor, tipo) VALUES ("TECNOLOGIA", "modelo", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("TERMINAL", "cadId", "LABORATORIO");
INSERT INTO parametros (chave, valor, tipo) VALUES ("DEFEITO1", "tipoDefeito", "LABORATORIO");









