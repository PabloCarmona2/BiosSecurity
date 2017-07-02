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
    Cliente bigint not null,
    primary key (NumServicio),
    foreign key (Propiedad) references Propiedades(IdProp),
    foreign key (Cliente) references Propiedades(Cliente)
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
    BajaLogica boolean not null,
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
    BajaLogica boolean not null,
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
    Cobrador bigint,
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


#INGRESO DE DATOS DE PRUEBA-----------------------------------------------------------------------------------------------------
#-------------------------------------------------------------------------------------------------------------------------------
INSERT INTO Empleados
VALUES (1, 'ben', 1111,20101010, 1000);
INSERT INTO Administradores
VALUES (1);
INSERT INTO Empleados
VALUES (4, 'diego', 1111,20101010, 1000);
INSERT INTO Administradores
VALUES (4);
INSERT INTO Empleados
VALUES (2, 'aaaaaben', 1111,20101010, 1000);
INSERT INTO Tecnicos
VALUES ('camaras',2);
INSERT INTO Empleados
VALUES (3, 'roberto', 1111,20101010, 1000);
INSERT INTO Tecnicos
VALUES ('alarmas',3);
INSERT INTO Empleados
VALUES (5, 'jose', 1111,20101010, 1000);
INSERT INTO Cobradores
VALUES ('camioneta',5);
INSERT INTO Empleados
VALUES (6, 'roberto', 1111,20101010, 1000);
INSERT INTO Cobradores
VALUES ('auto',6);

INSERT INTO Clientes
VALUES(7, 'martin', 'Goes', 'calle 1234', '111111');
INSERT INTO Clientes
VALUES(8, 'gaston', 'Goes', 'calle 1241', '113333');
INSERT INTO Clientes
VALUES(9, 'gonzalo', 'Malvin', 'calle 123214', '11112431');
INSERT INTO Clientes
VALUES(10, 'joaquin', 'Malvin', 'curva 13', '1122221');

INSERT INTO Propiedades
VALUES(1, 'apartamento', 'calle 45467', 7);
INSERT INTO Propiedades
VALUES(2, 'local', 'calle 41267', 7);
INSERT INTO Propiedades
VALUES(1, 'galpon', 'calle 45467', 8);
INSERT INTO Propiedades
VALUES(2, 'edificio', 'calle 1212467', 8);
INSERT INTO Propiedades
VALUES(1, 'teatro', 'calle 1231267', 9);
INSERT INTO Propiedades
VALUES(2, 'Supermercado', 'calle 4267', 9);
INSERT INTO Propiedades
VALUES(1, 'tienda', 'calle 1223467', 10);

INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#1
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#2
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#3
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#4
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#5
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#6
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#7
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#8
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#9
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#10
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#11
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#12
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#13
INSERT INTO Dispositivos(DescripcionUbicacion, BajaLogica)
VALUES(NULL, false);#14

INSERT INTO Servicios(Fecha, Monitoreo, Propiedad, Cliente)
VALUES(20161010, true, 1, 7);
INSERT INTO ServicioVideoVigilancia(Terminal, NumServicio)
VALUES(true, 1);
INSERT INTO Servicios(Fecha, Monitoreo, Propiedad, Cliente)
VALUES(20161010, false, 1, 8);
INSERT INTO ServicioVideoVigilancia(Terminal, NumServicio)
VALUES(true, 2);
INSERT INTO Servicios(Fecha, Monitoreo, Propiedad, Cliente)
VALUES(20161010, true, 2, 7);
INSERT INTO ServicioAlarmas(CodAnulacion, NumServicio)
VALUES(123456, 3);
INSERT INTO Servicios(Fecha, Monitoreo, Propiedad, Cliente)
VALUES(20161010, true, 1, 9);
INSERT INTO ServicioVideoVigilancia(Terminal, NumServicio)
VALUES(true, 4);
INSERT INTO Servicios(Fecha, Monitoreo, Propiedad, Cliente)
VALUES(20161010, true, 1, 10);
INSERT INTO ServicioVideoVigilancia(Terminal, NumServicio)
VALUES(true, 5);
INSERT INTO Servicios(Fecha, Monitoreo, Propiedad, Cliente)
VALUES(20161010, true, 2, 9);
INSERT INTO ServicioAlarmas(CodAnulacion, NumServicio)
VALUES(1267533, 6);

INSERT INTO Alarmas
VALUES(3, 1, 2, false);
INSERT INTO Alarmas
VALUES(3, 2, 2, false);
INSERT INTO Alarmas
VALUES(6, 3, 3, false);
INSERT INTO Alarmas
VALUES(6, 4, 3, false);
INSERT INTO Camaras
VALUES(false, 1, 5, 3, false);
INSERT INTO Camaras
VALUES(true, 2, 6, 2, false);
INSERT INTO Camaras
VALUES(true, 4, 7, 3, false);
INSERT INTO Camaras
VALUES(false, 5, 8, 2, false);
INSERT INTO Alarmas
VALUES(NULL, 9, NULL, false);
INSERT INTO Alarmas
VALUES(NULL, 10, NULL, false);
INSERT INTO Alarmas
VALUES(NULL, 11, NULL, false);
INSERT INTO Camaras
VALUES(NULL, NULL, 12, NULL, false);
INSERT INTO Camaras
VALUES(NULL, NULL, 13, NULL, false);
INSERT INTO Camaras
VALUES(NULL, NULL, 14, NULL, false);

/*INSERT INTO CabezalRecibo(Fecha, Total, Cliente,Cobrador, Cobrado)
VALUES(20170630, 10000, 7, 5, false);

INSERT INTO LineaRecibo
VALUES(5000, 1, 1);
INSERT INTO LineaRecibo
VALUES(5000, 1, 3);

INSERT INTO CabezalRecibo(Fecha, Total, Cliente,Cobrador, Cobrado)
VALUES(20170630, 10000, 8, 5, false);

INSERT INTO LineaRecibo
VALUES(10000, 2, 2);

INSERT INTO CabezalRecibo(Fecha, Total, Cliente,Cobrador, Cobrado)
VALUES(20170630, 10000, 9, 5, false);

INSERT INTO LineaRecibo
VALUES(5000, 3, 4);
INSERT INTO LineaRecibo
VALUES(5000, 3, 6);

INSERT INTO CabezalRecibo(Fecha, Total, Cliente,Cobrador, Cobrado)
VALUES(20170630, 10000, 10, 5, false);

INSERT INTO LineaRecibo
VALUES(10000, 4, 5);*/

#

#call ModificarAdministrador(1,'aaaa',9090,20101010,1000,@salida);
#call ModificarAdministrador(1,'aaaa',9090,)
#delete from administradores where cedula = 2;
#call modificarAdministrador(2);
#UPDATE Empleados SET Nombre = 'asas', Clave = 2121 , empleados.FIngreso=20101010 , empleados.Sueldo=1212 WHERE Cedula = 2;
#call BajaTecnico (4, @Salida);
#call desinstalarCamara(5,@salida);

#
#
#
#
#
#-------------------------------------------------------------------------------------------------------------------------------


#--------------------------------SP de Dispositivos.............................................................................


#-------------------------------------------------------------------------------------------------------------------------------
# -------------------------------SP Camara--------------------------------------------------------------------------------------

DELIMITER //

CREATE procedure AltaCamara(descripcion VARCHAR(100), exterior boolean, servicio bigint, tecnico bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    DECLARE idDispositivo bigint;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar el dispositivo correctamente!';
	
    
	INSERT INTO Dispositivos
    VALUES(descripcion);
    
    SELECT idDispositivo = NumInventario
    FROM Dispositivos
    ORDER BY NumInventario asc limit 1;
    
	SET mensajeError = 'No se pudo agregar la camara correctamente!.';
	
	INSERT INTO Camaras
	VALUES(exterior, servicio, idDispositivo, tecnico);
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure InstalarCamara(numeroInventario int, descripcion VARCHAR(100), exterior boolean, servicio bigint, tecnico bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    IF NOT EXISTS(SELECT * FROM Camaras WHERE NumInventario = numeroInventario) 
    THEN
			SET pError = 'No existe la camara que desea instalar!';
            
			LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS(SELECT * FROM ServicioVideoVigilancia WHERE NumServicio = servicio) 
    THEN
			SET pError = 'No existe el servicio en el que desea instalar el dispositivo!';
            
			LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS(SELECT * FROM Tecnicos WHERE Cedula = tecnico) 
    THEN
			SET pError = 'No existe el tecnico asignado a la instalacion!';
            
			LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo instalar el dispositivo correctamente!';
	
    
	UPDATE Dispositivos
    SET DescripcionUbicacion = descripcion
    WHERE NumInventario = numeroInventario;
    
	SET mensajeError = 'No se pudo instalar la camara correctamente!.';
	
	UPDATE Camaras
    SET Exterior = exterior, Servicio = servicio, Tecnico = tecnico
    WHERE NumInventario = numeroInventario;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure DesinstalarCamara(pnumeroInventario bigint,pError varchar(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    IF NOT EXISTS(SELECT * FROM Camaras WHERE NumInventario = pnumeroInventario) 
    THEN
			SET pError = 'No existe la camara que desea desinstalar!';
            
			LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    
	SET FOREIGN_KEY_CHECKS = 0;
    
	SET pError = 'No se pudo desinstalar la camara correctamente!';
    
	 UPDATE Dispositivos
    SET DescripcionUbicacion = null
    WHERE Dispositivos.NumInventario = pnumeroInventario;
	
    SET pError = 'No se pudo desinstalar el dispositivo correctamente!';
    
    UPDATE Camaras
    SET Servicio = null, Tecnico = null, Exterior = null
    WHERE Camaras.NumInventario = pnumeroInventario;
    
	SET FOREIGN_KEY_CHECKS = 1;
    
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure BajaCamara(numeroInventario int, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    IF NOT EXISTS(SELECT * FROM Camaras WHERE NumInventario = numeroInventario) 
    THEN
			SET pError = 'No existe la camara que desea eliminar!';
            
			LEAVE cuerpo;
	END IF;
    
    IF EXISTS(SELECT * FROM Camaras WHERE NumInventario = numeroInventario AND BajaLogica = 1) 
    THEN
			SET pError = 'La camara que desea eliminar ya esta dada de baja!';
            
			LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    SET FOREIGN_KEY_CHECKS = 0;
    IF EXISTS(SELECT * FROM Camaras WHERE NumInventario = numeroInventario AND Servicio != null)
    THEN
			SET mensajeError = 'No se pudo dar de baja la camara indicada!';
            
			UPDATE Camaras
            SET BajaLogica = 1
            WHERE NumInventario = numeroInventario;
            
            
			SET mensajeError = 'No se pudo dar de baja el dispositivo indicado!';
            
            UPDATE Dispositivos
            SET BajaLogica = 1
            WHERE NumInventario = numeroInventario;
            
            
            
	ELSE 
         
            SET mensajeError = 'No se pudo eliminar la camara indicada!';
            
			DELETE FROM Camaras
            WHERE NumInventario = numeroInventario;
            
			SET mensajeError = 'No se pudo dar eliminar el dispositivo indicado!';
            
            DELETE FROM Dispositivos
            WHERE NumInventario = numeroInventario;
           
        
	END IF;
	SET FOREIGN_KEY_CHECKS = 1;
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


#------------------------------------SP ALARMAS--------------------------------------------------------

DELIMITER //

CREATE procedure AltaAlarma(descripcion VARCHAR(100), servicio bigint, tecnico bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    DECLARE idDispositivo bigint;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar el dispositivo correctamente!';
	
    
	INSERT INTO Dispositivos
    VALUES(descripcion);
    
    SELECT idDispositivo = NumInventario
    FROM Dispositivos
    ORDER BY NumInventario asc limit 1;
    
	SET mensajeError = 'No se pudo agregar la alarma correctamente!.';
	
	INSERT INTO Alarmas
	VALUES(servicio, idDispositivo, tecnico);
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure InstalarAlarma(numeroInventario int, descripcion VARCHAR(100), servicio bigint, tecnico bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    IF NOT EXISTS(SELECT * FROM Alarmas WHERE NumInventario = numeroInventario) 
    THEN
			SET pError = 'No existe la alarma que desea instalar!';
            
			LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS(SELECT * FROM ServicioAlarmas WHERE NumServicio = servicio) 
    THEN
			SET pError = 'No existe el servicio en el que desea instalar el dispositivo!';
            
			LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS(SELECT * FROM Tecnicos WHERE Cedula = tecnico) 
    THEN
			SET pError = 'No existe el tecnico asignado a la instalacion!';
            
			LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo instalar el dispositivo correctamente!';
	
    
	UPDATE Dispositivos
    SET DescripcionUbicacion = descripcion
    WHERE NumInventario = numeroInventario;
    
	SET mensajeError = 'No se pudo instalar la alarma correctamente!.';
	
	UPDATE Alarmas
    SET Servicio = servicio, Tecnico = tecnico
    WHERE NumInventario = numeroInventario;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure DesinstalarAlarma(numeroInventario int, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    IF NOT EXISTS(SELECT * FROM Alarmas WHERE NumInventario = numeroInventario) 
    THEN
			SET pError = 'No existe la alarma que desea desinstalar!';
            
			LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    
	SET FOREIGN_KEY_CHECKS = 0;
    
<<<<<<< HEAD
	SET pError = 'No se pudo desinstalar el dispositivo correctamente!';
	#SET mensajeError = 'No se pudo desinstalar el dispositivo correctamente!';
=======
	SET mensajeError = 'No se pudo desinstalar el dispositivo correctamente!';
>>>>>>> 7083f51878665a69e2bf2ae23c683ac387c41d74
    
	UPDATE Dispositivos
    SET DescripcionUbicacion = null
    WHERE NumInventario = numeroInventario;
    
	SET pError = 'No se pudo desinstalar la alarma correctamente!.';
	#SET mensajeError = 'No se pudo desinstalar el dispositivo correctamente!';
	UPDATE Alarmas
    SET Servicio = null, Tecnico = null
    WHERE NumInventario = numeroInventario;
    
	SET FOREIGN_KEY_CHECKS = 1;
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


DELIMITER //

CREATE procedure BajaAlarma(numeroInventario int, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    IF NOT EXISTS(SELECT * FROM Alarmas WHERE NumInventario = numeroInventario) 
    THEN
			SET pError = 'No existe la alarma que desea eliminar!';
            
			LEAVE cuerpo;
	END IF;
    
    IF EXISTS(SELECT * FROM Alarmas WHERE NumInventario = numeroInventario AND BajaLogica = 1) 
    THEN
			SET pError = 'La alarma que desea eliminar ya esta dada de baja!';
            
			LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    
    SET FOREIGN_KEY_CHECKS = 0;
    
    IF EXISTS(SELECT * FROM Alarmas WHERE NumInventario = numeroInventario AND Servicio != null)
    THEN
			SET mensajeError = 'No se pudo dar de baja el dispositivo indicado!';
            
            UPDATE Dispositivos
            SET BajaLogica = 1
            WHERE NumInventario = numeroInventario;
            
            SET mensajeError = 'No se pudo dar de baja la camara indicada!';
            
			UPDATE Camaras
            SET BajaLogica = 1
            WHERE NumInventario = numeroInventario;
            
	ELSE 
         
            SET mensajeError = 'No se pudo eliminar la camara indicada!';
            
			DELETE FROM Camaras
            WHERE NumInventario = numeroInventario;
            
			SET mensajeError = 'No se pudo dar eliminar el dispositivo indicado!';
            
            DELETE FROM Dispositivos
            WHERE NumInventario = numeroInventario;
           
        
	END IF;
	
    SET FOREIGN_KEY_CHECKS = 1;
    
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


#-----------------------------------------------------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------


#---------------------------------SP Tecnicos---------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------


DELIMITER //

CREATE procedure AltaTecnico(pCedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, especializacion VARCHAR(7), OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF EXISTS(SELECT * FROM Tecnicos WHERE Cedula = pCedula)
    THEN
		SET pError = 'Ya existe el tecnico que desea ingresar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar el empleado correctamente!';
	
    
	INSERT INTO Empleados
    VALUES(pCedula, nombre, clave, fIngreso, sueldo);
    
	SET mensajeError = 'No se pudo agregar el tecnico correctamente!.';
	
	INSERT INTO Tecnicos
	VALUES(especializacion, pCedula);
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


DELIMITER //

CREATE procedure ModificarTecnico(pCedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, especializacion VARCHAR(7), OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF NOT EXISTS(SELECT * FROM Tecnicos WHERE Cedula = pCedula)
    THEN
		SET pError = 'No existe el tecnico que desea modificar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    
    SET FOREIGN_KEY_CHECKS = 0;
	
	SET mensajeError = 'No se pudo modificar el empleado correctamente!';
	
    
	UPDATE Empleados
    SET Nombre = nombre, Clave = clave, FIngreso = fIngreso, Sueldo = sueldo
    WHERE Cedula = pCedula;
    
	SET mensajeError = 'No se pudo modificar el tecnico correctamente!.';
	
	UPDATE Tecnicos
	SET Especializacion = especializacion
    WHERE Cedula = pCedula;
    
    SET FOREIGN_KEY_CHECKS = 1;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


DELIMITER //

CREATE procedure BajaTecnico(pCedula bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF NOT EXISTS(SELECT * FROM Tecnicos WHERE Cedula = pCedula)
    THEN
		SET pError = 'El tecnico que desea eliminar no existe en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    
    SET FOREIGN_KEY_CHECKS = 0;
	
	SET mensajeError = 'No se pudo eliminar el tecnico correctamente!';
	
    DELETE FROM Tecnicos WHERE Tecnicos.Cedula = pCedula;
	 
    
	SET mensajeError = 'No se pudo eliminar el empleado correctamente!.';
	
	DELETE FROM Empleados WHERE Empleados.Cedula = pCedula;
    
    SET FOREIGN_KEY_CHECKS = 1;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

#----------------------------------------------------------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------------


#-----------------------------------SP Recibo--------------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------------

DELIMITER //

CREATE procedure GenerarCabezalRecibo(fecha datetime, total double, cliente bigint, cobrador bigint, cobrado boolean, OUT pError VARCHAR(500), OUT pNumRecibo bigint)
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE sinErrores BIT;
    
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF sinErrores THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    SET sinErrores = 1;
	
	SET mensajeError = 'No se pudo agregar el recibo correctamente!';
	
    
	INSERT INTO CabezalRecibo(Fecha, Total, Cliente,Cobrador, Cobrado)
    VALUES(fecha, total, cliente, cobrador, cobrado);
    
    SET sinErrores = 0;
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure CobrarRecibo(pNumRecibo bigint, pCobrador bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE sinErrores BIT;
    
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF sinErrores THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    IF NOT EXISTS(SELECT * FROM CabezalRecibo WHERE NumRecibo = pNumRecibo)
    THEN
		SET pError = 'El recibo que desea marcar como cobrado no este en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    SET sinErrores = 1;
    
	SET mensajeError = 'No se pudo cobrar el recibo correctamente!';
	
    
	UPDATE CabezalRecibo
    SET Cobrado = true, Cobrador = pCobrador
    WHERE NumRecibo = pNumRecibo;
    
    SET sinErrores = 0;
	
END//

DELIMITER ;

#-----------------------------------------------------------------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------------------


#-------------------------------SP LineaRecibo--------------------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------------------

DELIMITER //

CREATE procedure RegistrarLineaEnRecibo(IN _importe double,IN _servicio bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE sinErrores BIT;
    DECLARE pNumRecibo BIGINT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF sinErrores THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    SET sinErrores = 1;
    
	SET mensajeError = 'No se pudo agregar la linea al recibo correctamente!';
    
    SET pNumRecibo = (SELECT MAX(NumRecibo) FROM CabezalRecibo);
    
    SET FOREIGN_KEY_CHECKS = 0;
    
	INSERT INTO LineaRecibo(Importe, NumRecibo, Servicio)
    VALUES(_importe, pNumRecibo, _servicio);
    
    SET FOREIGN_KEY_CHECKS = 1;
    
    SET sinErrores = 0;
	
END//

DELIMITER ;



#-------------------------------------SP SERVICIOS------------------------------------


#-------------------------------------SP SERVICIOS ALARMA-----------------------------

DELIMITER //

CREATE procedure AltaServicioAlarma(fecha datetime, monitoreo boolean, idprop bigint, cliente bigint, codanulacion bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo dar de alta el servicio correctamente!';
	
    
	INSERT INTO biossecurity.servicios VALUES(fecha, monitoreo, idprop, cliente);
    
	SET mensajeError = 'No se pudo dar de alta el servicio de alarma correctamente!.';
	
	INSERT INTO biossecurity.servicioalarmas VALUES(codanulacion, (select NumServicio from biossecurity.servicios order by NumServicio desc limit 1));
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure EditarServicioAlarma(pNumServicio bigint, pFecha datetime, pMonitoreo boolean, pPropiedad bigint, pCliente bigint, pCodAnulacion bigint, OUT pError varchar(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
 
    IF NOT EXISTS(SELECT * FROM biossecurity.servicios WHERE NumServicio = pNumServicio)
    THEN
		SET pError = 'No existe el servicio a modificar';
		LEAVE cuerpo;
    END IF;
	
	SET mensajeError = 'No se pudo modificar el servicio correctamente!';
    
	SET FOREIGN_KEY_CHECKS = 0;
    
	UPDATE biossecurity.servicios
    SET Fecha = pFecha, Monitoreo = pMonitoreo, Propiedad = pPropiedad, Cliente = pCliente
    WHERE NumServicio = pNumServicio;
    
    UPDATE biossecurity.servicioalarmas
    SET CodAnulacion = pCodAnulacion
    WHERE NumServicio = pNumServicio;
    
END//

DELIMITER ;


DELIMITER //

CREATE procedure EliminarServicioAlarma(pNumservicio bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF NOT EXISTS(SELECT * FROM biossecurity.servicios WHERE servicios.NumServicio = pNumservicio)
    THEN
		SET pError = 'El servicio que desea eliminar no existe en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo eliminar el servicio correctamente!';
	
    DELETE FROM biossecurity.servicioalarmas
    WHERE servicioalarmas.NumServicio = pNumservicio;
	 
    
	SET mensajeError = 'No se pudo eliminar el servicio de alarma correctamente!.';
	
	DELETE FROM biossecurity.servicios
    WHERE servicios.NumServicio = pNumservicio;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


DELIMITER //

CREATE PROCEDURE ServicioAlarmaXCliente(pCliente bigint)
BEGIN

SELECT *
FROM ServicioAlarmas INNER JOIN Servicios ON ServicioAlarmas.NumServicio = Servicios.NumServicio
WHERE Servicios.Cliente = pCliente;

END//

DELIMITER ;

#-------------------------------------------------------------------------------------


#-------------------------------------SP SERVICIOS CAMARA-----------------------------

DELIMITER //

CREATE PROCEDURE ServicioCamaraXCliente(pCliente bigint)
BEGIN

SELECT *
FROM ServicioVideoVigilancia INNER JOIN Servicios ON ServicioVideoVigilancia.NumServicio = Servicios.NumServicio
WHERE Servicios.Cliente = pCliente;

END//

DELIMITER ;

DELIMITER //

CREATE procedure AltaServicioVideo(fecha datetime, monitoreo boolean, idprop bigint, cliente bigint, terminal boolean, OUT pError VARCHAR(500)) 
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo dar de alta el servicio correctamente!';
	
    
	INSERT INTO biossecurity.servicios VALUES(fecha, monitoreo, idprop, cliente);
    
	SET mensajeError = 'No se pudo dar de alta el servicio de alarma correctamente!.';
	
	INSERT INTO biossecurity.servicioalarmas VALUES(terminal, (select NumServicio from biossecurity.servicios order by NumServicio desc limit 1));
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;




DELIMITER //

CREATE procedure EliminarServicioVideo(pNumservicio bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF NOT EXISTS(SELECT * FROM biossecurity.servicios WHERE servicios.NumServicio = pNumservicio)
    THEN
		SET pError = 'El servicio que desea eliminar no existe en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo eliminar el servicio correctamente!';
	
    DELETE FROM biossecurity.serviciovideovigilancia
    WHERE serviciovideovigilancia.NumServicio = pNumservicio;
	 
    
	SET mensajeError = 'No se pudo eliminar el servicio de videovigilancia correctamente!.';
	
	DELETE FROM biossecurity.servicios
    WHERE servicios.NumServicio = pNumservicio;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure EditarServicioVideo(pNumServicio bigint, pFecha datetime, pMonitoreo boolean, pPropiedad bigint, pCliente bigint, pTerminal boolean, OUT pError varchar(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
 
    IF NOT EXISTS(SELECT * FROM biossecurity.servicios WHERE NumServicio = pNumServicio)
    THEN
		SET pError = 'No existe el servicio a modificar';
		LEAVE cuerpo;
    END IF;
	
	SET mensajeError = 'No se pudo modificar el servicio correctamente!';
    
	SET FOREIGN_KEY_CHECKS = 0;
    
	UPDATE biossecurity.servicios
    SET Fecha = pFecha, Monitoreo = pMonitoreo, Propiedad = pPropiedad, Cliente = pCliente
    WHERE NumServicio = pNumServicio;
    
    UPDATE biossecurity.serviciovideovigilancia
    SET Terminal = pTerminal
    WHERE NumServicio = pNumServicio;
    
END//

DELIMITER ;

#-------------------------------------------------------------------------------------

#---------------------------------SP Administrativos---------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------


DELIMITER //

CREATE procedure AgregarAdministrador(pCedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double,OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;

    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar el empleado correctamente!';
	
    
	INSERT INTO empleados
    VALUES(pCedula, nombre, clave, fIngreso, sueldo);
    
	SET mensajeError = 'No se pudo agregar el admin correctamente!.';
	
	INSERT INTO administradores
	VALUES(pCedula);
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;
DELIMITER //

CREATE procedure ModificarAdministrador(pCedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, OUT pError varchar(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
 
    IF NOT EXISTS(SELECT * FROM administradores WHERE Cedula = pCedula)
    THEN
		SET pError = 'No existe el administrativo que desea modificar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
	
	SET mensajeError = 'No se pudo modificar el empleado correctamente!';
    
	SET FOREIGN_KEY_CHECKS = 0;
    
	UPDATE Empleados
    SET Nombre = nombre, Clave = clave, FIngreso = fIngreso, Sueldo = sueldo
    WHERE Cedula = pCedula;
    
END//

DELIMITER ;
DELIMITER //

CREATE procedure EliminarAdministrador(pCedula bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF NOT EXISTS(SELECT * FROM empleados WHERE Cedula = pCedula)
    THEN
		SET pError = 'El tecnico que desea eliminar no existe en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    
    SET FOREIGN_KEY_CHECKS = 0;
	
	SET mensajeError = 'No se pudo eliminar el tecnico correctamente!';
	
    DELETE FROM Administradores WHERE Administradores.Cedula = pCedula;
	 
    
	SET mensajeError = 'No se pudo eliminar el empleado correctamente!.';
	
	DELETE FROM Empleados WHERE Empleados.Cedula = pCedula;
    
    SET FOREIGN_KEY_CHECKS = 1;
	
	COMMIT;
    
    SET transaccionActiva = 0;
	
END//


DELIMITER ;

#---------------------------------SP Clientes---------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------
DELIMITER //

CREATE procedure ModificarCliente(pCedula bigint, nombre VARCHAR(25), barrio VARCHAR(20),  dirCobro VARCHAR(25),  telefono VARCHAR(20), OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
	
	
    IF NOT EXISTS(SELECT * FROM clientes WHERE Cedula = pCedula)
    THEN
		SET pError = 'No existe el cliente que desea modificar en el sistema!';
            
		LEAVE cuerpo;
    END IF;

	SET mensajeError = 'No se pudo modificar el cliente correctamente!';
	
    
	UPDATE clientes
    SET Nombre = nombre, Barrio = barrio, DirCobro = dircobro, Telefono = telefono
    WHERE Cedula = pCedula;
    
	
END//

DELIMITER ;

#---------------------------------SP Cobradores---------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------

DELIMITER //

CREATE procedure AgregarCobrador(pCedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, Transporte varchar(20), OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;

    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar el empleado correctamente!';
	
    
	INSERT INTO biossecurity.empleados
    VALUES(pCedula, nombre, clave, fIngreso, sueldo);
    
	SET mensajeError = 'No se pudo agregar el cobrador correctamente!.';
	
	INSERT INTO biossecurity.cobradores
	VALUES(Transporte, pCedula);
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure ModificarCobrador(pCedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, pTransporte varchar(20), OUT pError varchar(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
 
    IF NOT EXISTS(SELECT * FROM biossecurity.empleados WHERE Cedula = pCedula)
    THEN
		SET pError = 'No existe el cobrador que desea modificar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
	
	SET mensajeError = 'No se pudo modificar el cobrador correctamente!';
    
	SET FOREIGN_KEY_CHECKS = 0;
    
	UPDATE biossecurity.empleados
    SET Nombre = nombre, Clave = clave, FIngreso = fIngreso, Sueldo = sueldo
    WHERE Cedula = pCedula;
    
    update biossecurity.cobradores
    set Transporte = pTransporte
    where Cedula = pCedula;
    
END//

DELIMITER ;

DELIMITER //

CREATE procedure EliminarCobrador(pCedula bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF NOT EXISTS(SELECT * FROM empleados WHERE Cedula = pCedula)
    THEN
		SET pError = 'El cobrador que desea eliminar no existe en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
    
    SET FOREIGN_KEY_CHECKS = 0;
	
	SET mensajeError = 'No se pudo eliminar el cobrador correctamente!';
	
    DELETE FROM biossecurity.cobradores WHERE cobradores.Cedula = pCedula;
	 
    
	SET mensajeError = 'No se pudo eliminar el empleado correctamente!.';
	
	DELETE FROM Empleados WHERE Empleados.Cedula = pCedula;
    
    SET FOREIGN_KEY_CHECKS = 1;
	
	COMMIT;
    
    SET transaccionActiva = 0;
	
END//


DELIMITER ;