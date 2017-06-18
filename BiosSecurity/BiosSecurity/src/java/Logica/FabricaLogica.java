/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.Interfaces.ILogicaDispositivo;
import Logica.Trabajo.LogicaDispositivo;



/**
 *
 * @author Geronimo
 */
public class FabricaLogica {
    public static ILogicaDispositivo GetLogicaDispositivo()
    {
        return (LogicaDispositivo.GetInstancia());
    }
}
