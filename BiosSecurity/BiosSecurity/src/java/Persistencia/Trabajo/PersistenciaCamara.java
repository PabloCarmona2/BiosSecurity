/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Camara;
import DataTypes.ServicioVideoVigilancia;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaCamara;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
