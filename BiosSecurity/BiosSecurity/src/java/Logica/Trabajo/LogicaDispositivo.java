/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.Dispositivo;
import Logica.Interfaces.ILogicaDispositivo;
import Persistencia.FabricaPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class LogicaDispositivo implements ILogicaDispositivo{
    private static LogicaDispositivo _instancia = null;
    private LogicaDispositivo() { }
    public static LogicaDispositivo GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaDispositivo();
        return _instancia;
    }
    
    public static void Validar(Dispositivo dispositivo) throws Exception
    {
        try{
            if (dispositivo == null)
            {
                throw new Exception("El dispositivo no puede ser nulo.");
            }
            if (dispositivo.getDescripcionUbicacion() != null && dispositivo.getDescripcionUbicacion().length() > 100 || dispositivo.getDescripcionUbicacion() != null && dispositivo.getDescripcionUbicacion().length() == 0)
            {
                throw new Exception("La descripcion de la ubicacion del dispositivo no puede tener mas de 100 caracteres o estar vacia.");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    
    public Dispositivo Buscar(int numInventario) throws Exception{
        try{
            Dispositivo dispositivo = null;
        
            dispositivo = FabricaPersistencia.GetPersistenciaAlarma().Buscar(numInventario);

            if(dispositivo == null){
                dispositivo = FabricaPersistencia.GetPersistenciaCamara().Buscar(numInventario);
            }
            
            return dispositivo;
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Agregar(Dispositivo dispositivo) throws Exception{
        try{
            
            Validar(dispositivo);
            
            if(dispositivo instanceof Camara){
                FabricaPersistencia.GetPersistenciaCamara().Agregar((Camara)dispositivo);
            }else if(dispositivo instanceof Alarma){
                FabricaPersistencia.GetPersistenciaAlarma().Agregar((Alarma)dispositivo);
            }else{
                throw new Exception("No existe el tipo de dispositivo que quiere ingresar");
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Eliminar(Dispositivo dispositivo) throws Exception{
        try{
            
            if(dispositivo instanceof Camara){
                FabricaPersistencia.GetPersistenciaCamara().Eliminar((Camara)dispositivo);
            }else if(dispositivo instanceof Alarma){
                FabricaPersistencia.GetPersistenciaAlarma().Eliminar((Alarma)dispositivo);
            }else{
                throw new Exception("No existe el tipo de dispositivo que quiere ingresar");
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public List<Dispositivo> Listar(String criterio) throws Exception {
        try{
            List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
            
            if(criterio == null || criterio.length() == 0){
                
                
                List<Alarma> alarmas = new ArrayList<Alarma>();
                List<Camara> camaras = new ArrayList<Camara>();

                alarmas = FabricaPersistencia.GetPersistenciaAlarma().Listar();
                camaras = FabricaPersistencia.GetPersistenciaCamara().Listar();

                dispositivos.addAll(alarmas);
                dispositivos.addAll(camaras);

                return dispositivos;
                
            }else{
                
                Dispositivo dispositivo = this.Buscar(Integer.parseInt(criterio));
                dispositivos.add(dispositivo);
                
                return dispositivos;
                
            }
            
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
}
