create table movimentacao_financeira (
	codigo bigint not null auto_increment, 
	codigo_origem bigint not null, 
	codigo_categoria bigint not null,
	data_registro datetime not null, 
	data_referencia date not null, 
	valor decimal(19,2) not null,
	FOREIGN KEY (codigo_origem) REFERENCES dominio_origem(codigo),
	FOREIGN KEY (codigo_categoria) REFERENCES dominio_categoria(codigo),
	primary key (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO movimentacao_financeira (codigo_origem, codigo_categoria, data_registro, data_referencia, valor) values (1, 1, '2021-01-27 12:00:00', '2021-01-27', 100 );