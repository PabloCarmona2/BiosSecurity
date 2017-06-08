/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import Persistencia.Interfaces.IPersistenciaTecnico;

/**
 *
 * @author Geronimo
 */
public class PersistenciaTecnico implements IPersistenciaTecnico{
    private static PersistenciaTecnico _instancia = null;
    private PersistenciaTecnico() { }
    public static PersistenciaTecnico GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaTecnico();
        return _instancia;
    }
}
