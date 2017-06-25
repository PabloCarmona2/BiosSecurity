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
#
#
#
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
    
    IF EXISTS(SELECT * FROM Camaras WHERE NumInventario = numeroInventario AND Servicio != null)
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
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


#-----------------------------------------------------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------


#---------------------------------SP Tecnicos---------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------


DELIMITER //

CREATE procedure AltaTecnico(cedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, especializacion VARCHAR(7), OUT pError VARCHAR(500))
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
    
    
    IF EXISTS(SELECT * FROM Tecnicos WHERE Cedula = cedula)
    THEN
		SET pError = 'Ya existe el tecnico que desea ingresar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar el empleado correctamente!';
	
    
	INSERT INTO Empleados
    VALUES(cedula, nombre, clave, fIngreso, sueldo);
    
	SET mensajeError = 'No se pudo agregar el tecnico correctamente!.';
	
	INSERT INTO Tecnicos
	VALUES(especializacion, cedula);
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


DELIMITER //

CREATE procedure ModificarTecnico(cedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, especializacion VARCHAR(7), OUT pError VARCHAR(500))
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
    
    
    IF NOT EXISTS(SELECT * FROM Tecnicos WHERE Cedula = cedula)
    THEN
		SET pError = 'No existe el tecnico que desea modificar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo modificar el empleado correctamente!';
	
    
	UPDATE Empleados
    SET Nombre = nombre, Clave = clave, FIngreso = fIngreso, Sueldo = sueldo
    WHERE Cedula = cedula;
    
	SET mensajeError = 'No se pudo modificar el tecnico correctamente!.';
	
	UPDATE Tecnicos
	SET Especializacion = especializacion
    WHERE Cedula = cedula;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;


DELIMITER //

CREATE procedure BajaTecnico(cedula bigint, OUT pError VARCHAR(500))
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
    
    
    IF NOT EXISTS(SELECT * FROM Tecnicos WHERE Cedula = cedula)
    THEN
		SET pError = 'El tecnico que desea eliminar no existe en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo eliminar el tecnico correctamente!';
	
    DELETE FROM Tecnicos
    WHERE Cedula = cedula;
	 
    
	SET mensajeError = 'No se pudo eliminar el empleado correctamente!.';
	
	DELETE FROM Empleados
    WHERE Cedula = cedula;
	
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
	
    
	INSERT INTO CabezalRecibo
    VALUES(fecha, total, cliente, cobrador, cobrado);
    
    SET sinErrores = 0;
    
    SELECT pNumRecibo = NumRecibo FROM CabezalRecibo ORDER BY NumRecibo asc limit 1;
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure Cobrar(numRecibo bigint, cobrador bigint, OUT pError VARCHAR(500))
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
    
    IF NOT EXISTS(SELECT * FROM CabezalRecibo WHERE NumRecibo = numRecibo)
    THEN
		SET pError = 'El recibo que desea marcar como cobrado no este en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    SET sinErrores = 1;
    
	SET mensajeError = 'No se pudo cobrar el recibo correctamente!';
	
    
	UPDATE CabezalRecibo
    SET Cobrado = true, Cobrador = cobrador;
    
    SET sinErrores = 0;
	
END//

DELIMITER ;

#-----------------------------------------------------------------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------------------


#-------------------------------SP LineaRecibo--------------------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------------------

DELIMITER //

CREATE procedure RegistrarLineaEnRecibo(importe double, numRecibo bigint, servicio bigint, OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    DECLARE totalActual double;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
        END IF;
		
		SET pError = mensajeError;
	END;
    
    
    IF NOT EXISTS(SELECT * FROM CabezalRecibo WHERE NumRecibo = numRecibo)
    THEN
		SET pError = 'No existe el recibo en el que quiere agregar la linea!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar la linea al recibo correctamente!';
	
    
	INSERT INTO LineaRecibo
    VALUES(importe, numRecibo, servicio);
    
	SET mensajeError = 'No se pudo sumar el importe de la linea al total del recibo correctamente!.';
	
    
    
    SELECT totalActual = Total FROM CabezalRecibo WHERE NumRecibo = numRecibo;
    
	UPDATE CabezalRecibo
	SET Total = (totalActual + importe)
    WHERE NumRecibo = numRecibo;
	
	COMMIT;
    
    SET transaccionActiva = 0;
	
END//

DELIMITER ;



#-------------------------------------SP SERVICIOS------------------------------------


#-------------------------------------SP SERVICIOS ALARMA-----------------------------

DELIMITER //

CREATE procedure AltaServicioAlarma(fecha datetime, monitoreo boolean, idprop bigint, cliente bigint, codanulacion bigint)
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

CREATE PROCEDURE ServicioAlarmaXCliente(cliente bigint)
BEGIN

SELECT *
FROM ServicioAlarmas INNER JOIN Servicios
	
WHERE Servicios.Cliente = cliente;

END//

DELIMITER ;

#-------------------------------------------------------------------------------------


#-------------------------------------SP SERVICIOS CAMARA-----------------------------

DELIMITER //

CREATE PROCEDURE ServicioCamaraXCliente(cliente bigint)
BEGIN

SELECT *
FROM ServicioVideoVigilancia INNER JOIN Servicios
WHERE Servicios.Cliente = cliente;

END//

DELIMITER ;

#-------------------------------------------------------------------------------------

#---------------------------------SP Administrativos---------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------


DELIMITER //

CREATE procedure AgregarAdministrador(cedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, OUT pError VARCHAR(500))
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
    
    
    IF EXISTS(SELECT * FROM Administradores WHERE Cedula = cedula)
    THEN
		SET pError = 'Ya existe el administrador que desea ingresar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo agregar el administrativo correctamente!';
	
    
	INSERT INTO Empleados
    VALUES(cedula, nombre, clave, fIngreso, sueldo);
    
	SET mensajeError = 'No se pudo agregar el administrador correctamente!.';
	
	INSERT INTO administrador
	 VALUES(nombre, clave, fIngreso, sueldo);
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

DELIMITER //

CREATE procedure ModificarAdministrador(cedula bigint, nombre VARCHAR(25), clave VARCHAR(20), fIngreso datetime, sueldo double, OUT pError VARCHAR(500))
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
    
    
    IF NOT EXISTS(SELECT * FROM Administradores WHERE Cedula = cedula)
    THEN
		SET pError = 'No existe el administrador que desea modificar en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo modificar el empleado correctamente!';
	
    
	UPDATE Empleados
    SET Nombre = nombre, Clave = clave, FIngreso = fIngreso, Sueldo = sueldo
    WHERE Cedula = cedula;
    
	SET mensajeError = 'No se pudo modificar el administrador correctamente!.';
	
	UPDATE administradores
    SET Nombre = nombre, Clave = clave, FIngreso = fIngreso, Sueldo = sueldo
    WHERE Cedula = cedula;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;
DELIMITER //

CREATE procedure EliminarAdministrador(cedula bigint, OUT pError VARCHAR(500))
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
    
    
    IF NOT EXISTS(SELECT * FROM administradores WHERE Cedula = cedula)
    THEN
		SET pError = 'El administrador que desea eliminar no existe en el sistema!';
            
		LEAVE cuerpo;
    END IF;
    
    
    SET transaccionActiva = 1;
    
	START TRANSACTION; 
	
	SET mensajeError = 'No se pudo eliminar el admiistrador correctamente!';
	
    DELETE FROM administradores
    WHERE Cedula = cedula;
	 
    
	SET mensajeError = 'No se pudo eliminar el empleado correctamente!.';
	
	DELETE FROM Empleados
    WHERE Cedula = cedula;
	
	COMMIT;
    
    SET transaccionActiva = 0;
    
	
END//

DELIMITER ;

#---------------------------------SP Clientes---------------------------------------------------------------------
#-----------------------------------------------------------------------------------------------------------------
DELIMITER //

CREATE procedure ModificarCliente(cedula bigint, nombre VARCHAR(25), barrio VARCHAR(20),  dirCobro VARCHAR(25),  telefono VARCHAR(20), OUT pError VARCHAR(500))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
	
	
    IF NOT EXISTS(SELECT * FROM clientes WHERE Cedula = cedula)
    THEN
		SET pError = 'No existe el cliente que desea modificar en el sistema!';
            
		LEAVE cuerpo;
    END IF;

	SET mensajeError = 'No se pudo modificar el cliente correctamente!';
	
    
	UPDATE clientes
    SET Nombre = nombre, Barrio = barrio, DirCobro = dircobro, Telefono = telefono
    WHERE Cedula = cedula;
    
	
END//

DELIMITER ;