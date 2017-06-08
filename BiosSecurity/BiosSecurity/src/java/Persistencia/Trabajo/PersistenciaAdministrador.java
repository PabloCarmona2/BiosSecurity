/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaAdministrador;

/**
 *
 * @author Geronimo
 */
public class PersistenciaAdministrador implements IPersistenciaAdministrador {
    private static PersistenciaAdministrador _instancia = null;
    private PersistenciaAdministrador() { }
    public static PersistenciaAdministrador GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaAdministrador();
        return _instancia;
    }
    
    
    
}
