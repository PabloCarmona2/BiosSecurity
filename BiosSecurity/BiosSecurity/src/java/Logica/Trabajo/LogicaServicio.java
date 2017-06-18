/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import Logica.Interfaces.ILogicaServicio;
import Persistencia.FabricaPersistencia;

/**
 *
 * @author Geronimo
 */
public class LogicaServicio implements ILogicaServicio{
    private static LogicaServicio _instancia = null;
    private LogicaServicio() { }
    public static LogicaServicio GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaServicio();
        return _instancia;
    }
    
    public static void Validar(Servicio servicio) throws Exception
    {
        try{
            if (servicio == null)
            {
                throw new Exception("El servicio no puede ser nulo.");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public void InstalarDispositivo(Servicio servicio) throws Exception{
        
        Validar(servicio);
        
        try{
            if(servicio instanceof ServicioVideoVigilancia){
                FabricaPersistencia.getPersistenciaVideoVigilancia().InstalarDispositivo((ServicioVideoVigilancia)servicio);
            }
            else if(servicio instanceof ServicioAlarma){
                FabricaPersistencia.getPersistenciaServicioAlarma().InstalarDispositivo((ServicioAlarma)servicio);
            }
            else{
                throw new Exception("Tipo de servicio inexistente!.");
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
}
