/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Persistencia.Interfaces.IPersistenciaAdministrador;
import Persistencia.Trabajo.PersistenciaAdministrador;
import Persistencia.Interfaces.IPersistenciaAlarma;
import Persistencia.Interfaces.IPersistenciaCamara;
<<<<<<< HEAD
import Persistencia.Interfaces.IPersistenciaCliente;
=======
>>>>>>> 7a98a246124cda750f9f6dcf42e6a3fb80c0874d
import Persistencia.Interfaces.IPersistenciaCobrador;
import Persistencia.Interfaces.IPersistenciaRecibo;
import Persistencia.Interfaces.IPersistenciaServicioAlarma;
import Persistencia.Interfaces.IPersistenciaTecnico;
import Persistencia.Trabajo.PersistenciaAlarma;
import Persistencia.Trabajo.PersistenciaCamara;
<<<<<<< HEAD
import Persistencia.Trabajo.PersistenciaCliente;
=======
>>>>>>> 7a98a246124cda750f9f6dcf42e6a3fb80c0874d
import Persistencia.Trabajo.PersistenciaCobrador;
import Persistencia.Trabajo.PersistenciaRecibo;
import Persistencia.Trabajo.PersistenciaServicioAlarma;
import Persistencia.Trabajo.PersistenciaTecnico;

/**
 *
 * @author Geronimo
 */
public class FabricaPersistencia {
    public static IPersistenciaTecnico GetPersistenciaTecnico()
    {
        return (PersistenciaTecnico.GetInstancia());
    }
    
    public static IPersistenciaCobrador GetPersistenciaCobrador()
    {
        return (PersistenciaCobrador.GetInstancia());
    }
    
     public static IPersistenciaAdministrador GetPersistenciaAdministrador()
    {
        return (PersistenciaAdministrador.GetInstancia());
    }   
    public static IPersistenciaAlarma GetPersistenciaAlarma()
    {
        return (PersistenciaAlarma.GetInstancia());
    }
    
    public static IPersistenciaCamara GetPersistenciaCamara()
    {
        return (PersistenciaCamara.GetInstancia());
    }
    
    public static IPersistenciaRecibo GetPersistenciaRecibo()
    {
        return (PersistenciaRecibo.GetInstancia());
    }
    public static IPersistenciaCobrador getPersistenciaCobrador()
    {
        return (PersistenciaCobrador.GetInstancia());
    }
     public static IPersistenciaCliente getPersistenciaCliente()
    {
        return (PersistenciaCliente.GetInstancia());
    }
     public static IPersistenciaServicioAlarma getPersistenciaServicioAlarma()
    {
        return (PersistenciaServicioAlarma.GetInstancia());
    }
}
