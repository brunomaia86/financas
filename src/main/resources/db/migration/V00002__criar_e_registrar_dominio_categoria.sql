CREATE TABLE dominio_categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO dominio_categoria (descricao) values ('Moradia');
INSERT INTO dominio_categoria (descricao) values ('Transporte');
INSERT INTO dominio_categoria (descricao) values ('Alimentação');
