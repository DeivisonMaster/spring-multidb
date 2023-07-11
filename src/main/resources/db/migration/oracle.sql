alter session set current_schema=aluno_revisao;

create table autor(
	id bigint primary key auto_increment,
	nome varchar(50)
);