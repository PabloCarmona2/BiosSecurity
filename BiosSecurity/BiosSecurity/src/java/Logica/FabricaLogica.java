/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.Interfaces.ILogicaDispositivo;
import Logica.Interfaces.ILogicaEmpleado;
import Logica.Trabajo.LogicaDispositivo;
import Logica.Trabajo.LogicaEmpleado;



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
    
}
