CREATE TABLE ativos (
	id 					BIGINT(20) 		PRIMARY KEY AUTO_INCREMENT,
	tipo				VARCHAR(50),
	modelo				VARCHAR(50),
	fabricante			VARCHAR(50), 
	serial_number		VARCHAR(50),
	cad_id				VARCHAR(50),
	cm_mac				VARCHAR(50),
	emta_mac			VARCHAR(50),
	estado				VARCHAR(50),
	tipo_defeito		VARCHAR(50),
	localizacao			VARCHAR(50),
	status				VARCHAR(10),
	data_importacao 	DATETIME,
	data_processamento 	DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


	