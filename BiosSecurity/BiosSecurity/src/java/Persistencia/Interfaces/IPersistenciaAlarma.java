/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Alarma;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaAlarma {
    Alarma Buscar(int numInventario) throws Exception;
    void Agregar(Alarma camara) throws Exception;
    void Instalar(Alarma camara) throws Exception;
    void Eliminar(Alarma camara) throws Exception;
}
