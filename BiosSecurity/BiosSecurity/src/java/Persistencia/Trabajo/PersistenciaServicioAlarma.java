/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import Persistencia.Interfaces.IPersistenciaServicioAlarma;

/**
 *
 * @author Geronimo
 */
public class PersistenciaServicioAlarma implements IPersistenciaServicioAlarma{
    private static PersistenciaServicioAlarma _instancia = null;
    private PersistenciaServicioAlarma() { }
    public static PersistenciaServicioAlarma GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaServicioAlarma();
        return _instancia;
    }
    
    public void InstalarDispositivo(ServicioAlarma servicio) throws Exception{
        try
            {
                if (!servicio.getDispositivos().isEmpty())
                {
                    PersistenciaAlarma.GetInstancia().Instalar((Alarma)servicio.getDispositivos().get((servicio.getDispositivos().size() - 1)), servicio.getNumServicio());
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
