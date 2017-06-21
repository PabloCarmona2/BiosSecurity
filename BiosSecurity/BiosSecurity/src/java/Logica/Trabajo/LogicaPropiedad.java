/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Propiedad;
import Logica.Interfaces.ILogicaPropiedad;

/**
 *
 * @author Geronimo
 */
public class LogicaPropiedad implements ILogicaPropiedad{
    private static LogicaPropiedad _instancia = null;
    private LogicaPropiedad() { }
    public static LogicaPropiedad GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaPropiedad();
        return _instancia;
    }
    public static void Validar(Propiedad casa) throws Exception
    {
        try{
         
            if(casa.getTipo().length() > 25 || casa.getTipo().length() == 0){
                throw new Exception("El tipo de la propiedad no puede tener mas de 25 caracteres o estar vacío!");
            }
            if(casa.getDireccion().length() > 25 || casa.getDireccion().length() == 0){
                throw new Exception("El tipo de la propiedad no puede tener mas de 25 caracteres o estar vacío!");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }   
    }
    public void Modificar(Propiedad casa) throws Exception{
        Validar(casa);
        
        Persistencia.FabricaPersistencia.getPersistenciaPropiedad().Modificar(casa);
    
    }
}
