/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.Interfaces.ILogicaCliente;
import Logica.Interfaces.ILogicaDispositivo;
import Logica.Interfaces.ILogicaEmpleado;
import Logica.Interfaces.ILogicaPropiedad;
import Logica.Trabajo.LogicaCliente;
import Logica.Trabajo.LogicaDispositivo;
import Logica.Trabajo.LogicaEmpleado;
import Logica.Trabajo.LogicaPropiedad;



/**
 *
 * @author Geronimo
 */
public class FabricaLogica {
    public static ILogicaDispositivo GetLogicaDispositivo()
    {
        return (LogicaDispositivo.GetInstancia());
    }
    
    public static ILogicaEmpleado GetLogicaEmpleado()
    {
        return (LogicaEmpleado.GetInstancia());
    }
    public static ILogicaPropiedad GetLogicaPropiedad(){
        
        return (LogicaPropiedad.GetInstancia());
    }
    public static ILogicaCliente GetLogicaCliente(){
    
            return (LogicaCliente.GetInstancia());
    }
    
}
