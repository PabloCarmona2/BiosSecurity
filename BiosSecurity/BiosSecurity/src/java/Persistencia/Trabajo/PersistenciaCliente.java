/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaCliente;

/**
 *
 * @author Geronimo
 */
public class PersistenciaCliente implements IPersistenciaCliente{
    private static PersistenciaCliente _instancia = null;
    private PersistenciaCliente() { }
    public static PersistenciaCliente GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaCliente();
        return _instancia;
    }
}
