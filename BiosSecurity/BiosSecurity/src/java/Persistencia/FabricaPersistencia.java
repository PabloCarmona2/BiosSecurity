/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Persistencia.Interfaces.IPersistenciaTecnico;
import Persistencia.Trabajo.PersistenciaTecnico;

/**
 *
 * @author Geronimo
 */
public class FabricaPersistencia {
    public static IPersistenciaTecnico GetPersistenciaTecnico()
    {
        return (PersistenciaTecnico.GetInstancia());
    }
}
