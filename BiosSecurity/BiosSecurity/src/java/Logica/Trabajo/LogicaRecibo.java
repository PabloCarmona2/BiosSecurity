/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Cliente;
import DataTypes.Recibo;
import DataTypes.Servicio;
import Logica.Interfaces.ILogicaRecibo;
import Persistencia.FabricaPersistencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class LogicaRecibo implements ILogicaRecibo{
    
    private static LogicaRecibo _instancia = null;
    
    private LogicaRecibo() { }
    
    public static LogicaRecibo GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaRecibo();
        return _instancia;
    }
    
    public static void Validar(Recibo recibo) throws Exception
    {
        try{
            if (recibo == null)
            {
                throw new Exception("El servicio no puede ser nulo.");
            }
            if(recibo.getCliente() == null){
                throw new Exception("El recibo debe pertenecer a algun cliente.");
            }
            if(recibo.getLineas() == null){
                throw new Exception("No se puede cobrar un recibo que no tiene lineas, su suma total seria siempre 0!.");
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public Recibo Buscar(int numRecibo) throws Exception{
        try{
            
            return FabricaPersistencia.GetPersistenciaRecibo().Buscar(numRecibo);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void GenerarRecibos(List<Recibo> recibos) throws Exception{
        try{
            
            FabricaPersistencia.GetPersistenciaRecibo().GenerarRecibos(recibos);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Cobrar(Recibo recibo) throws Exception{
        try{
            
            Validar(recibo);
            
            FabricaPersistencia.GetPersistenciaRecibo().Cobrar(recibo);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    public HashMap<Cliente, List<Servicio>> ClientesYServiciosOrdenados() throws Exception{
        return null;
    }
}
