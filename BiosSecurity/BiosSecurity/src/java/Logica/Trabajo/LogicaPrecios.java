/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Precios;
import Logica.Interfaces.ILogicaPrecios;
import Persistencia.FabricaPersistencia;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Geronimo
 */
public class LogicaPrecios implements ILogicaPrecios {
    
    private static LogicaPrecios _instancia = null;
    private LogicaPrecios() { }
    public static LogicaPrecios GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaPrecios();
        return _instancia;
    }
    
    public static void Validar(Precios precios) throws Exception
    {
        try{
         
            if(precios.getTasaAlarmas() > 100){
                throw new Exception("La tasa por monitoreo de alarmas no puede superar el 100%");
            }
            if(precios.getTasaCamaras()> 100){
                throw new Exception("La tasa por monitoreo de camaras no puede superar el 100%");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }   
    }
    
    public Precios Obtener(String ruta) throws FileNotFoundException, IOException, Exception{
        try{
            return FabricaPersistencia.GetPersistenciaPrecios().Obtener(ruta);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Actualizar(Precios pPrecios, String ruta) throws FileNotFoundException, IOException, Exception{
        Validar(pPrecios);
        
        try{
            
            FabricaPersistencia.GetPersistenciaPrecios().Actualizar(pPrecios, ruta);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
