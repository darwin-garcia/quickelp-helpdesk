create database Quickelp ;
use Quickelp;

create table TipoDocumento(
idTipoDocumento int primary key not null auto_increment,
nombreTipoDoc varchar(3) not null,
);
insert into TipoDocumento(nombreTipoDoc) values ('CC');
insert into TipoDocumento(nombreTipoDoc) values ('TI');
insert into TipoDocumento(nombreTipoDoc) values ('CE');
insert into TipoDocumento(nombreTipoDoc) values ('NIT');
insert into TipoDocumento(nombreTipoDoc) values ('PS');

create table Rol(
idRol int primary key not null auto_increment,
NombreRol varchar(25) not null
);
insert into Rol (NombreRol) values ('Administrador');
insert into Rol (NombreRol) values ('Funcionario');
insert into Rol (NombreRol) values ('Cliente');

create table Ubicacion (
idUbicacion int primary key not null auto_increment,
direccion varchar(50) not null,
barrio varchar(40) not null,
localidad varchar (60)
);

create table tipoServicio(
idTiposervicio int primary key not null auto_increment,
nombreTipoServicio varchar(15)
);
insert into tipoServicio(nombreTipoServicio) values('Mantenimiento');
insert into tipoServicio(nombreTipoServicio) values('Reparacion');
insert into tipoServicio(nombreTipoServicio) values('Instalacion');
insert into tipoServicio(nombreTipoServicio) values('Configuracion');
insert into tipoServicio(nombreTipoServicio) values('Consulta');
insert into tipoServicio(nombreTipoServicio) values('Respaldo');

create table estadoServicio(
idEstadoServicio int primary key not null auto_increment,
nombreEstadoServicio varchar(15)
);
insert into estadoServicio(nombreEstadoServicio) values('Completo');
insert into estadoServicio(nombreEstadoServicio) values('En proceso');
insert into estadoServicio(nombreEstadoServicio) values('Pendiente');
insert into estadoServicio(nombreEstadoServicio) values('Incompleto');
insert into estadoServicio(nombreEstadoServicio) values('Cancelado'); 

create table Usuario (
idUsuario int primary key not null auto_increment,
numeroIdentificacion decimal(12,0) unique not null,
nombre varchar(45) not null,
apellido varchar(45) not null,
telefono decimal(12,0) not null,
correo varchar(50) unique not null,
direccion varchar(75) not null,
claveUsuario varchar(40) not null,
idRol int,
foreign key (idRol) references Rol(idRol),
idTipoDocumento int,
foreign key (idTipoDocumento) references TipoDocumento(idTipoDocumento)
);

create table Funcionario(
idFuncionario int primary key not null auto_increment,
idusuario int not null,
nombreEspecialidad varchar(45),
Estado int not null,
foreign key(idusuario) references Usuario(idUsuario)
);

create table registroEquipo(
idRegistro int primary key not null auto_increment,
tipoEquipo varchar(25) not null,
numeroSerie varchar(30) unique not null,
modelo varchar(30) not null,
marca varchar(25) not null,
observaciones varchar(300) not null,
idUsuario int,
foreign key (idUsuario) references Usuario(idUsuario)
);
create table solicitudServicio(
idServicio int primary key not null auto_increment,
fechaSolicitud datetime not null,
descripcionServicio varchar(200),
calificacion int,
idRegistro int,
foreign key (idRegistro) references registroEquipo(idRegistro),
idUsuario int,
foreign key(idUsuario) references Usuario(idUsuario),
idFuncionario int,
foreign key (idFuncionario) references Funcionario(idFuncionario),
idUbicacion int,
foreign key (idUbicacion) references Ubicacion(idUbicacion),
idTiposervicio int,
foreign key (idTiposervicio) references tipoServicio(idTiposervicio),
idEstadoServicio int,
foreign key (idEstadoServicio) references estadoServicio(idEstadoServicio)
);
 create table Diagnostico(
 idDiagnostico int primary key not null auto_increment,
 fechaRecepcion datetime not null,
 observaciones varchar(200),
 posibleSolucion varchar(200),
 idServicio int,
foreign key (idServicio) references solicitudServicio(idServicio)
 );
 
 create table Reparacion(
idReparacion int primary key not null auto_increment,
fechaFinalizacion datetime not null,
observacionesProceso varchar(200) not null,
garantia varchar(200) not null,
fechaEntrega datetime not null,
idServicio int,
foreign key (idServicio) references solicitudServicio(idServicio)
);


insert into Usuario(numeroIdentificacion, nombre, apellido,telefono,correo,direccion,claveUsuario, idRol, idTipoDocumento) 
values(1030589075,'Darwin','Garcia',3104600220,'dagarcia5709@misena.edu.co','Calle 5B 29-45','sena2019',1,1);
insert into Usuario(numeroIdentificacion, nombre, apellido,telefono,correo,direccion,claveUsuario, idRol, idTipoDocumento) 
values(1018489750,'Felipe','Delgado',3195027722,'fdelgado057@misena.edu.co','Calle 5B 29-45','sena2019',1,1);




