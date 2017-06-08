/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Camara;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaCamara {
    Camara Buscar(int numInventario) throws Exception;
    void Agregar(Camara camara) throws Exception;
    void Instalar(Camara camara) throws Exception;
    void Eliminar(Camara camara) throws Exception;
}
