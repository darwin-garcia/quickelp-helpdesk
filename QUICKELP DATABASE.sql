create database Quickelp ;
use Quickelp;

create table TipoDoc(
idTipoDoc int primary key not null auto_increment,
nombreTipoDoc varchar(6) not null
);
insert into TipoDoc(nombreTipoDoc) values ('CC');
insert into TipoDoc(nombreTipoDoc) values ('NIT');
insert into TipoDoc(nombreTipoDoc) values ('TI');

create table Rol(
idRol int primary key not null auto_increment,
NombreRol varchar(25) not null
);
insert into Rol (NombreRol) values ('Administrador');
insert into Rol (NombreRol) values ('Funcionario');
insert into Rol (NombreRol) values ('Cliente');

create table tipoServicio(
idTiposer int primary key not null auto_increment,
tiposervicio varchar(15) not null
);
insert into tipoServicio(tiposervicio) values('InstalSoftware');
insert into tipoServicio(tiposervicio) values('InstalHardware');
insert into tipoServicio(tiposervicio) values('Mantenimiento');
insert into tipoServicio(tiposervicio) values('Revisión');

create table Usuario (
idUsuario int primary key not null auto_increment,
numIdentidad bigint unique not null,
nombre varchar(45) not null,
apellido varchar(45) not null,
telefono bigint not null,
correo varchar(50) unique not null,
clave varchar(40) not null,
direccion varchar(75) not null,
idRol int,
foreign key (idRol) references Rol(idRol),
idTipoDoc int,
foreign key (idTipoDoc) references TipoDoc(idTipoDoc)
);

create table Usuario_Tiposer(
idUsutipo int primary key not null auto_increment,
idUsuario int,
foreign key (idUsuario) references Usuario(idUsuario),
idTiposer int,
foreign key (idTiposer) references tipoServicio(idTiposer)
);

create table Marca(
idMarca int primary key not null auto_increment,
nombreMarca varchar(30)
);
insert into Marca(nombreMarca) values('Acer');
insert into Marca(nombreMarca) values('AOC');
insert into Marca(nombreMarca) values('Apple');
insert into Marca(nombreMarca) values('Asus');
insert into Marca(nombreMarca) values('Benq');
insert into Marca(nombreMarca) values('Brother');
insert into Marca(nombreMarca) values('Compaq');
insert into Marca(nombreMarca) values('Compumax');
insert into Marca(nombreMarca) values('Dell');
insert into Marca(nombreMarca) values('Epson');
insert into Marca(nombreMarca) values('eMachines');
insert into Marca(nombreMarca) values('Gateway');
insert into Marca(nombreMarca) values('Generico');
insert into Marca(nombreMarca) values('Gigabyte');
insert into Marca(nombreMarca) values('Huawei');
insert into Marca(nombreMarca) values('IBM');
insert into Marca(nombreMarca) values('HP');
insert into Marca(nombreMarca) values('Lanix');
insert into Marca(nombreMarca) values('Lenovo');
insert into Marca(nombreMarca) values('LG');
insert into Marca(nombreMarca) values('MSI');
insert into Marca(nombreMarca) values('NEC');
insert into Marca(nombreMarca) values('Ricoh');
insert into Marca(nombreMarca) values('Samsung');
insert into Marca(nombreMarca) values('Sony');
insert into Marca(nombreMarca) values('Toshiba');
insert into Marca(nombreMarca) values('ViewSonic');
insert into Marca(nombreMarca) values('Xiaomi');
insert into Marca(nombreMarca) values('Xerox');
insert into Marca(nombreMarca) values('ZTE');

create table Equipo(
idEquipo int primary key not null auto_increment,
numSerial varchar (30) unique not null,
modelo varchar(30) not null,
idUsuario int,
foreign key (idUsuario) references Usuario(idUsuario),
idMarca int,
foreign key (idMarca) references Marca(idMarca)
);

create table Estado(
idEstado int primary key not null auto_increment,
nombreEstado varchar(40) not null
);
insert into Estado(nombreEstado) values('Activo');
insert into Estado(nombreEstado) values('Inactivo');

create table Servicio(
idServicio int primary key not null auto_increment,
fechaSolicitud datetime not null,
descripcion varchar(200) not null,
idUsuario int,
idTiposer int,
idEquipo int,
foreign key (idEquipo) references Equipo(idEquipo), 
idEstado int ,
foreign key (idEstado) references Estado(idEstado)
);


create table Diagnostico(
 idDiagnostico int primary key not null auto_increment,
 fechaRecepcion datetime not null,
 observaciones varchar(200) not null,
 idServicio int,
foreign key (idServicio) references Servicio(idServicio)
 );
 
 create table Reparacion(
idReparacion int primary key not null auto_increment,
fechaEntrega datetime not null,
observaciones varchar(200) not null,
garantia varchar(200) not null,
fechaFinal datetime not null,

idServicio int,
foreign key (idServicio) references Servicio(idServicio)
);

-- Registro Administradores
insert into Usuario(numIdentidad, nombre, apellido,telefono,correo,direccion,clave, idRol, idTipoDoc) 
values(1030589075,'Darwin','Garcia',3104600220,'dagarcia5709@misena.edu.co','Calle 5B 29-45','sena2019',1,1);
insert into Usuario(numIdentidad, nombre, apellido,telefono,correo,direccion,clave, idRol, idTipoDoc) 
values(1018489750,'Felipe','Delgado',3195027722,'fdelgado057@misena.edu.co','Calle 5B 29-45','sena2019',1,1);
insert into Usuario(numIdentidad, nombre, apellido,telefono,correo,direccion,clave, idRol, idTipoDoc) 
values(1233911787,'Valentina','Rueda',3204948498,'vrueda03@misena.edu.co','Cll 52b 29-45 ','sena2019',1,1);
insert into Usuario(numIdentidad, nombre, apellido,telefono,correo,direccion,clave, idRol, idTipoDoc) 
values(1010124811,'Michell','Cabrera',3122167003,'mscabrera1@misena.edu.co','Calle 18a 14-53','sena2019',1,1);

-- Registro cliente
insert into Usuario(idRol, idTipoDoc, numIdentidad, nombre, apellido,telefono,correo,direccion,clave) 
values(3,1,2154632,'Lina','Lopez',32561489,'lina@gmail.com','Calle flsa 2','cliente2');

-- Registro Clientes
delimiter $$
create procedure registroCliente(in _idRol int, in _idTipoDoc int, in _numIdentidad bigint, in _nombre varchar(45), in_apellido varchar(45), in _telefono bigint, in _correo varchar(50), in _direccion int, in _clave varchar(40))
begin 
insert into Usuario(idRol, idTipoDoc, numIdentidad, nombre, apellido,telefono,correo,direccion,clave)
values(3, _idTipoDoc, __numIdentidad, _nombre, _apellido, _telefono, _correo, _direccion, _clave);
end $$
DELIMITER;
call registroCliente(3,  _idTipoDoc, __numIdentidad, _nombre, _apellido, _telefono, _correo, _direccion, _clave);
-- PRUEBA REGISTRO
select * from Usuario;

-- Registro Equipo
insert into Equipo( idUsuario, idMarca, numSerial, modelo) 
values( 3, 1, '45sd68540', '0modelo');
-- PRUEBA REGISTRO
select * from Equipo;

-- Registro Equipo
delimiter $$
create procedure registroEquipo(in _idEquipo int, _idUsuario int, in _idMarca int, in _numSerial varchar(30), in _modelo varchar(30))
begin
insert into Equipo(idEquipo, idUsuario, idMarca, numSerial, modelo) 
values(_idEquipo, _idUsuario, _idMarca, _numSerial, _modelo);
end;

-- PENDIENTE POR DUDA CON LA TABLA DE REFERENCIA LOGICA(Tiposer)
insert into Servicio( idUsuario, descripcion, idTiposer, idEquipo, idMarca) 
values(3, 'No enciende mi PC',1,1,1);
 
 -- PENDIENTE POR DUDA CON LA TABLA DE REFERENCIA LOGICA(Tiposer)
-- Solicitud Servicio
delimiter $$
create procedure solictudServicio(in idServicio int, in _descripcion varchar(200), in _idUsuario int, in idEquipo int, in _idDetalleUsuTiposer int)
begin
insert into Equipo(idUsuario, idMarca, numSerial, modelo) 
values(1, 1, '45sd68540','0modelo');
end;
insert into Servicio( idUsuario, descripcion, idDetalleUsuTiposer, idEquipo, idMarca) 
values(3, 'No enciende mi PC',4,1,1);

insert into diagnostico( observaciones, idServicio ) 
values('Equipo no enciende',1);
end;

-- Asignar Servicio a un tecnico
DELIMITER $$
create procedure asignarServicio(in _idUsuario int, in _idServicio int, in _idEstado int)
begin
insert into Servicio(idUsuario, idServicio, idEstado) 
values(2, _idServicio, _idEstado);
end;
-- Informacion que recibe el tecnico para el soporte
select * from Servicio

-- NECESITO EXPLICACION DE ESTO!!
inner join usuario on usuario.idusuario=asginarServicio.idUsuarioTecnico
inner join servicio on servicio.idservicio=asginarServicio.idServicio;

DELIMITER @@
create procedure actualizarUsuario(idUsu int,telefono bigint, direccion varchar(75), clave varchar(40))
BEGIN
UPDATE Usuario SET
telefono=telefono,
direccion=direccion,
clave=clave
where idUsuario=idUsu;
END @@
DELIMITER ;

-- Ejemplo de llamado
call actualizarUsuario(1,31545135, 'callle falsa', 'sena56');
 select * from Usuario;


