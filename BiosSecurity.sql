DROP database IF EXISTS BiosSecurity;

create database BiosSecurity;

use BiosSecurity;

create table Empleados (
	Cedula bigint primary key,
    Nombre varchar(25) not null,
    Clave varchar(20) not null,
    FIngreso datetime not null,
    Sueldo double not null
);

create table Tecnicos (
	Especializacion varchar(7) not null,
    Empleado bigint not null,
    foreign key (Empleado) references Empleados(Cedula),
    primary key (Empleado)
);

create table Cobradores (
	Transporte varchar(20),
    Empleado bigint not null,
    foreign key (Empleado) references Empleados(Cedula),
    primary key (Empleado)
);

create table Clientes (
	Cedula bigint primary key,
    Nombre varchar(25) not null,
    Barrio varchar(20) not null,
    DirCobro varchar(50) not null,
    Telefono varchar(20) not null
);

create table Propiedades (
	IdProp bigint auto_increment not null,
    Tipo varchar(20) not null,
    Direccion varchar(50) not null,
    Cliente bigint not null,
    primary key (IdProp),
    foreign key (Cliente) references Clientes(Cedula)
);

create table Dispositivos (
	NumInventario bigint auto_increment not null,
    Tipo varchar(6) not null,
    primary key (NumInventario)
);

create table Servicios (
	NumServicio bigint auto_increment not null,
    Fecha datetime not null,
    Monitoreo boolean not null,
    Propiedad bigint not null,
    primary key (NumServicio),
    foreign key (Propiedad) references Propiedades(IdProp)
);

create table ServicioVideo (
	Terminal boolean not null,
    NumServicio bigint not null,
    foreign key (NumServicio) references Servicios(NumServicio),
    primary key (NumServicio)
);

create table ServicioAlarma (
	CodAnulacion bigint not null,
    NumServicio bigint not null,
    foreign key (NumServicio) references Servicios(NumServicio),
    primary key (NumServicio)
);

create table DispositivoServicioAlarma (
	Descripcion varchar(100) not null,
    Servicio bigint not null,
    Dispositivo bigint not null,
    Tecnico bigint not null,
	foreign key (Servicio) references ServicioAlarma(NumServicio),
    foreign key (Dispositivo) references Dispositivos(NumInventario),
    foreign key (Tecnico) references Tecnicos(Empleado),
    primary key (Dispositivo, Servicio)
);

create table DispositivoServicioVideo (
	Descripcion varchar(100) not null,
    Exterior Boolean not null,
    Servicio bigint not null,
    Dispositivo bigint not null,
    Tecnico bigint not null,
	foreign key (Servicio) references ServicioVideo(NumServicio),
    foreign key (Dispositivo) references Dispositivos(NumInventario),
    foreign key (Tecnico) references Tecnicos(Empleado),
    primary key (Dispositivo, Servicio)
);

create table CabezalRecibo (
	NumRecibo bigint auto_increment not null,
    Fecha datetime not null,
    Total double not null,
    Cliente bigint not null,
    Cobrador bigint not null,
    foreign key (Cliente) references Clientes(Cedula),
    foreign key (Cobrador) references Cobradores(Empleado),
    primary key (NumRecibo)
);

create table LineaRecibo(
	Importe double not null,
    Recibo bigint not null,
    Servicio bigint not null,
	foreign key (Recibo) references CabezalRecibo(NumRecibo),
    foreign key (Servicio) references Servicios(NumServicio),
    primary key (Recibo, Servicio)
);


