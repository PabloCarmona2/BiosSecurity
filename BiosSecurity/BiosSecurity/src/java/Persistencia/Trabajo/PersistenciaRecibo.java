/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaRecibo;

/**
 *
 * @author Geronimo
 */
public class PersistenciaRecibo implements IPersistenciaRecibo{
    private static PersistenciaRecibo _instancia = null;
    private PersistenciaRecibo() { }
    public static PersistenciaRecibo GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaRecibo();
        return _instancia;
    }
}
