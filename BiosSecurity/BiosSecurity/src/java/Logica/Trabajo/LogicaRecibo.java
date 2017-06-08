/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import Logica.Interfaces.ILogicaRecibo;

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
}
