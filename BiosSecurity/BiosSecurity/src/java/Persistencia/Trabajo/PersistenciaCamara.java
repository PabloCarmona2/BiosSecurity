/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaCamara;

/**
 *
 * @author Geronimo
 */
public class PersistenciaCamara implements IPersistenciaCamara{
    private static PersistenciaCamara _instancia = null;
    private PersistenciaCamara() { }
    public static PersistenciaCamara GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaCamara();
        return _instancia;
    }
    
}
