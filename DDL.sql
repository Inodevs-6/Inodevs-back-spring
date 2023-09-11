drop schema if exists inodevs;

create schema inodevs;

drop user if exists 'user'@'localhost';

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on inodevs.* to user@'localhost';

use inodevs;

create table desc_cargo (
    desc_id bigint unsigned not null auto_increment,
    desc_vaga varchar(50) not null,
    desc_nivel varchar(50) not null,
    desc_habilidades text,
    desc_atitudes text,
    desc_capacidades text,
    primary key (desc_id)
);

create table candidato (
    cand_id bigint unsigned not null auto_increment,
    cand_experiencia text not null,
    cand_contato varchar(50) not null,
    cand_nome varchar(50) not null,
    cand_pontos_cha int,
    primary key (cand_id)
);

create table empresa (
    emp_id bigint unsigned not null auto_increment,
    emp_nome varchar(50) not null,
    emp_cnpj varchar(18) not null,
    emp_descricao text,
    primary key (emp_id),
    unique key (emp_cnpj)
);

create table desc_cargo_edit (
    desc_edit_id bigint unsigned not null auto_increment,
    desc_edit_vaga varchar(50) not null,
    desc_edit_nivel varchar(20) not null,
    desc_edit_habilidades text,
    desc_edit_atitudes text,
    desc_edit_capacidades text,
    emp_id bigint unsigned not null,
    primary key (desc_edit_id),
    foreign key emp_cargo_fk (emp_id) references empresa (emp_id) on delete restrict on update cascade
);

create table desc_cargo_candidato (
    desc_id bigint unsigned not null,
    cand_id bigint unsigned not null,
    primary key (desc_id, cand_id),
    foreign key cand_usuario_fk (desc_id) references desc_cargo (desc_id) on delete restrict on update cascade,
    foreign key cand_candidato_fk (cand_id) references candidato (cand_id) on delete restrict on update cascade
);