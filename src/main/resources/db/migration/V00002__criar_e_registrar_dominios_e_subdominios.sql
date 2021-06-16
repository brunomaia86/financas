CREATE TABLE dominio (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_dominio (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	codigoDominio BIGINT(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

INSERT INTO dominio (descricao) values ('Origem');
INSERT INTO item_dominio (descricao, codigoDominio) values ('Débito', 1);
INSERT INTO item_dominio (descricao, codigoDominio) values ('Crédito', 2);

