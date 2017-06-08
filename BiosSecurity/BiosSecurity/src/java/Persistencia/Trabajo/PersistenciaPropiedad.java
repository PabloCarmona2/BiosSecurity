/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaPropiedad;

/**
 *
 * @author Geronimo
 */
public class PersistenciaPropiedad implements IPersistenciaPropiedad{
    private static PersistenciaPropiedad _instancia = null;
    private PersistenciaPropiedad() { }
    public static PersistenciaPropiedad GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaPropiedad();
        return _instancia;
    }
}
