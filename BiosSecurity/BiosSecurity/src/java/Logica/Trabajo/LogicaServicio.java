/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import Logica.Interfaces.ILogicaServicio;

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
}
