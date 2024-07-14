create table topicos
(
    id             bigint       not null auto_increment,
    titulo         varchar(50)  not null,
    mensaje        varchar(300) not null,
    fecha_creacion datetime     not null,
    autor_id          bigint,
    curso          varchar(50)  not null,

    primary key (id),
    foreign key (autor_id) references usuarios(id)
);
