/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaLineaRecibo;

/**
 *
 * @author Geronimo
 */
public class PersistenciaLineaRecibo implements IPersistenciaLineaRecibo{
    private static PersistenciaLineaRecibo _instancia = null;
    private PersistenciaLineaRecibo() { }
    public static PersistenciaLineaRecibo GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaLineaRecibo();
        return _instancia;
    }
}
