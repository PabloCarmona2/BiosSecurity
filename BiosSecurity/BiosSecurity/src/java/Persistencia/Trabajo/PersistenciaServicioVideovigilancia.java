/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaServicioVideoVigilancia;

/**
 *
 * @author Geronimo
 */
public class PersistenciaServicioVideovigilancia implements IPersistenciaServicioVideoVigilancia{
    private static PersistenciaServicioVideovigilancia _instancia = null;
    private PersistenciaServicioVideovigilancia() { }
    public static PersistenciaServicioVideovigilancia GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaServicioVideovigilancia();
        return _instancia;
    }
}
