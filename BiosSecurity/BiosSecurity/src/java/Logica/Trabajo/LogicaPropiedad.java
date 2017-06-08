/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

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
}
