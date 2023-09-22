drop schema if exists inodevs;

create schema inodevs;

drop user if exists 'user'@'localhost';

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on inodevs.* to user@'localhost';

use inodevs;

create table empresa (
    emp_id bigint unsigned not null auto_increment,
    emp_nome varchar(50) not null,
    emp_cnpj varchar(18) not null,
    emp_descricao text,
    primary key (emp_id),
    unique key (emp_cnpj)
);

create table vaga (
    vaga_id bigint unsigned not null auto_increment,
    vaga_nome varchar(50) not null,
    vaga_nivel varchar(50) not null,
    vaga_conhecimentos text,
    vaga_habilidades text,
    vaga_atitudes text,
    primary key (vaga_id)
);

create table candidato (
    cand_id bigint unsigned not null auto_increment,
    cand_nome varchar(50) not null,
    cand_experiencia text not null,
    cand_contato varchar(50) not null,
    primary key (cand_id)
);

create table empresa_vaga (
    vaga_id bigint unsigned not null,
    emp_id bigint unsigned not null,
    primary key (emp_id, vaga_id),
    foreign key vaga_emp_fk (vaga_id) references vaga (vaga_id) on delete restrict on update cascade,
    foreign key emp_vaga_fk (emp_id) references empresa (emp_id) on delete restrict on update cascade
);

create table candidato_vaga (
    vaga_id bigint unsigned not null,
    cand_id bigint unsigned not null,
    cand_vaga_rank int not null,
    cand_vaga_pontos_cha int not null,
    primary key (vaga_id, cand_id),
    foreign key vaga_cand_fk (vaga_id) references vaga (vaga_id) on delete restrict on update cascade,
    foreign key cand_vaga_fk (cand_id) references candidato (cand_id) on delete restrict on update cascade
);