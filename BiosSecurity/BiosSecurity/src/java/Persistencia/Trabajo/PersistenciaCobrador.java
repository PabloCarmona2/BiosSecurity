/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaCobrador;

/**
 *
 * @author Geronimo
 */
public class PersistenciaCobrador implements IPersistenciaCobrador{
    private static PersistenciaCobrador _instancia = null;
    private PersistenciaCobrador() { }
    public static PersistenciaCobrador GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaCobrador();
        return _instancia;
    }
}
