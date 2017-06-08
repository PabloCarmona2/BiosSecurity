/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaAlarma;

/**
 *
 * @author Geronimo
 */
public class PersistenciaAlarma implements IPersistenciaAlarma {
    private static PersistenciaAlarma _instancia = null;
    private PersistenciaAlarma() { }
    public static PersistenciaAlarma GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaAlarma();
        return _instancia;
    }
    
}
