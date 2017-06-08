/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import Logica.Interfaces.ILogicaEmpleado;

/**
 *
 * @author Geronimo
 */
public class LogicaEmpleado implements ILogicaEmpleado{
    private static LogicaEmpleado _instancia = null;
    private LogicaEmpleado() { }
    public static LogicaEmpleado GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaEmpleado();
        return _instancia;
    }
}
