SET GLOBAL log_bin_trust_function_creators = 1;
--
CREATE DATABASE if not exists JuegosDeMesa;
--
USE JuegosDeMesa;
--
CREATE TABLE IF NOT EXISTS disenadores (
iddisenador int auto_increment primary key,
nombre varchar(50) not null,
apellidos varchar(150) not null,
fechanacimiento date,
pais varchar(50));
--
CREATE TABLE IF NOT EXISTS editoriales (
ideditorial int auto_increment primary key,
editorial varchar(50)  not null,
email varchar(100) not null,
telefono varchar(9),
productos varchar(50),
web varchar(500));
--
CREATE TABLE IF NOT EXISTS juegos (
idjuego int auto_increment primary key,
titulo varchar(50) not null,
isbn varchar(40) not null UNIQUE,
duracion varchar(20) not null,
jugadores varchar(20) not null,
ideditorial int not null,
genero varchar(30),
iddisenador int not null,
precio float not null,
fechapublicacion date);
--
alter table juegos
add foreign key (ideditorial) references editoriales(ideditorial),
add foreign key (iddisenador) references disenadores(iddisenador);
--
delimiter ||
create function existeIsbn(f_isbn varchar(40))
returns bit
begin
	declare i int;
	set i=0;
	while (i<(select max(idjuego) from juegos)) do
	if ((select isbn from juegos 
		 where idjuego=(i+1)) like f_isbn) 
	then return 1;
	end if;
	set i=i+1;
	end while;
	return 0;
end; ||
delimiter ;
--
delimiter ||
create function existeNombreEditorial (f_name varchar(50))
returns bit
begin
	declare i int;
	set i=0;
	while (i<(select max(ideditorial) from editoriales)) do
	if ((select editorial from editoriales 
	     where ideditorial = (i+1)) like f_name) 
	then return 1;
	end if;
	set i=i+1;
	end while;
	return 0;
end; ||
delimiter ;
--
delimiter ||
create function existeNombreDisenador (f_name varchar(50))
returns bit
begin
	declare i int;
	set i=0;
	while (i<(select max(iddisenador) from disenadores)) do
	if ((select concat(apellidos,', ',nombre) from autores
		where iddisenador = (i+1)) like f_name)
	then return 1;
	end if;
	set i=i+1;
	end while;
	return 0;
end; ||
delimiter ;
--
delimiter ||
create function contarDisenadores ()
returns int
begin
	declare i int;
	set i=0;
	while (i<(select count(iddisenador) from disenadores)) do
	set i=i+1;
	end while;
	return i;
end; ||
delimiter ;
--
delimiter ||
create function contarEditoriales ()
returns int
begin
	declare i int;
	set i=0;
	while (i<(select count(ideditorial) from editoriales)) do
	set i=i+1;
	end while;
	return i;
end; ||
delimiter ;
--
delimiter ||
create function contarJuegos ()
returns int
begin
	declare i int;
	set i=0;
	while (i<(select count(idjuego) from juegos)) do
	set i=i+1;
	end while;
	return i;
end; ||
delimiter ;