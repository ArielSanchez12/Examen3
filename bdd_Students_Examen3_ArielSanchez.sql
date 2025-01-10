create database BaseDos;
use BaseDos;

create table Students(
id int auto_increment primary key,
cedula varchar(30),
materia1 double,
materia2 double,
materia3 double,
materia4 double,
materia5 double,
promedio double
);

select * from Students;