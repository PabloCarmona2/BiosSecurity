/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import Logica.Interfaces.ILogicaDispositivo;

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
}
