create database BaseUno;
use BaseUno;

create table Users(
id int auto_increment primary key,
usuario varchar(30),
contrasenia varchar(30)
);

insert into Users(usuario,contrasenia) 
values ('ariel', 'sanchez');

select * from Users;