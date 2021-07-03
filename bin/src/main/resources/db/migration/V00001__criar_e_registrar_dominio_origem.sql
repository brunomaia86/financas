CREATE TABLE dominio_origem (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO dominio_origem (descricao) values ('Débito');
INSERT INTO dominio_origem (descricao) values ('Crédito');