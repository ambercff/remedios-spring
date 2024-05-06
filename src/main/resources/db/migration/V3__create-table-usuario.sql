CREATE TABLE usuario (
    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(100) not null,
    nome varchar(100) not null,
    role varchar(20) not null,


    primary key(id)
);