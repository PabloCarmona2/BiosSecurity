/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Cobrador;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaCobrador {
    
    Cobrador LoginCobrador(int cedula, String clave)throws Exception;
    
}
