/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaServicioAlarma;

/**
 *
 * @author Geronimo
 */
public class PersistenciaServicioAlarma implements IPersistenciaServicioAlarma{
    private static PersistenciaServicioAlarma _instancia = null;
    private PersistenciaServicioAlarma() { }
    public static PersistenciaServicioAlarma GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaServicioAlarma();
        return _instancia;
    }
}
