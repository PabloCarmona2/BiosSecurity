/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Propiedad;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaPropiedad {
    
    public Propiedad Buscar(int id) throws Exception;
    public void Modificar(Propiedad casa) throws Exception;
}
