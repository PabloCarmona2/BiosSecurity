/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.ServicioVideoVigilancia;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaServicioVideoVigilancia {
    void InstalarDispositivo(ServicioVideoVigilancia servicio) throws Exception;
}
