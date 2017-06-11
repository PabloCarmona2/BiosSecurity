/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import Persistencia.Interfaces.IPersistenciaServicioVideoVigilancia;

/**
 *
 * @author Geronimo
 */
public class PersistenciaServicioVideovigilancia implements IPersistenciaServicioVideoVigilancia{
    private static PersistenciaServicioVideovigilancia _instancia = null;
    private PersistenciaServicioVideovigilancia() { }
    public static PersistenciaServicioVideovigilancia GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaServicioVideovigilancia();
        return _instancia;
    }
    
    public void InstalarDispositivo(ServicioVideoVigilancia servicio) throws Exception{
        try
            {
                if (!servicio.getDispositivos().isEmpty())
                {
                    PersistenciaCamara.GetInstancia().Instalar((Camara)servicio.getDispositivos().get((servicio.getDispositivos().size() - 1)), servicio.getNumServicio());
                }
                else
                {
                    throw new Exception("No hay dispositivos para instalar.");
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.getMessage());
            }
    }
}
