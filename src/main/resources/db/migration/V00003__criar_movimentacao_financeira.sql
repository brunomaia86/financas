create table movimentacao_financeira (
	codigo bigint not null auto_increment, 
	codigo_origem bigint not null, 
	referencia date not null, 
	registro datetime not null, 
	valor decimal(19,2) not null, 
	primary key (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;