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
    Cedula bigint not null,
    foreign key (Cedula) references Empleados(Cedula),
    primary key (Cedula)
);

create table Cobradores (
	Transporte varchar(20),
    Cedula bigint not null,
    foreign key (Cedula) references Empleados(Cedula),
    primary key (Cedula)
);

create table Administradores (
    Cedula bigint not null,
    foreign key (Cedula) references Empleados(Cedula),
    primary key (Cedula)
);

create table Clientes (
	Cedula bigint primary key,
    Nombre varchar(25) not null,
    Barrio varchar(20) not null,
    DirCobro varchar(50) not null,
    Telefono varchar(20) not null
);

create table Propiedades (
	IdProp bigint not null,
    Tipo varchar(20) not null,
    Direccion varchar(50) not null,
    Cliente bigint not null,
    foreign key (Cliente) references Clientes(Cedula),
    primary key (IdProp, Cliente)
);

create table Dispositivos (
	NumInventario bigint auto_increment not null,
    DescripcionUbicacion varchar(100),
    primary key (NumInventario),
    BajaLogica boolean not null
);

create table Servicios (
	NumServicio bigint auto_increment not null,
    Fecha datetime not null,
    Monitoreo boolean not null,
    Propiedad bigint not null,
    primary key (NumServicio),
    foreign key (Propiedad) references Propiedades(IdProp)
);

create table ServicioVideoVigilancia (
	Terminal boolean not null,
    NumServicio bigint not null,
    foreign key (NumServicio) references Servicios(NumServicio),
    primary key (NumServicio)
);

create table ServicioAlarmas (
	CodAnulacion bigint not null,
    NumServicio bigint not null,
    foreign key (NumServicio) references Servicios(NumServicio),
    primary key (NumServicio)
);

create table Alarmas (
    Servicio bigint,
    NumInventario bigint not null,
    Tecnico bigint,
	foreign key (Servicio) references ServicioAlarmas(NumServicio),
    foreign key (NumInventario) references Dispositivos(NumInventario),
    foreign key (Tecnico) references Tecnicos(Cedula),
    primary key (NumInventario)
);

create table Camaras (
    Exterior boolean,
    Servicio bigint,
    NumInventario bigint not null,
    Tecnico bigint,
	foreign key (Servicio) references ServicioVideoVigilancia(NumServicio),
    foreign key (NumInventario) references Dispositivos(NumInventario),
    foreign key (Tecnico) references Tecnicos(Cedula),
    primary key (NumInventario)
);

create table CabezalRecibo (
	NumRecibo bigint auto_increment not null,
    Fecha datetime not null,
    Total double not null,
    Cliente bigint not null,
    Cobrador bigint not null,
    Cobrado boolean not null,
    foreign key (Cliente) references Clientes(Cedula),
    foreign key (Cobrador) references Cobradores(Cedula),
    primary key (NumRecibo)
);

create table LineaRecibo(
	Importe double not null,
    NumRecibo bigint not null,
    Servicio bigint not null,
	foreign key (NumRecibo) references CabezalRecibo(NumRecibo),
    foreign key (Servicio) references Servicios(NumServicio),
    primary key (NumRecibo, Servicio)
);
