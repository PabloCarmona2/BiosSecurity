/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.ServicioAlarma;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaServicioAlarma {
    void InstalarDispositivo(ServicioAlarma servicio) throws Exception;
    ServicioAlarma Buscar(int numeroServicio)throws Exception;
    
    
}
