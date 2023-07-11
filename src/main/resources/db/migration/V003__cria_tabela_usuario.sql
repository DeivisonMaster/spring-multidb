use auth;
create table usuario(
	id bigint primary key auto_increment,
	nome varchar(50),
	email varchar(50),
	senha varchar(50)
);