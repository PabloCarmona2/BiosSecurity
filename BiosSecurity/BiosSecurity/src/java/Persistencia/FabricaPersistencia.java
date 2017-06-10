/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Persistencia.Interfaces.IPersistenciaAlarma;
import Persistencia.Interfaces.IPersistenciaCamara;
import Persistencia.Interfaces.IPersistenciaRecibo;
import Persistencia.Interfaces.IPersistenciaTecnico;
import Persistencia.Trabajo.PersistenciaAlarma;
import Persistencia.Trabajo.PersistenciaCamara;
import Persistencia.Trabajo.PersistenciaRecibo;
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
}
