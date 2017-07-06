/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaServicioVideoVigilancia {
    void InstalarDispositivo(ServicioVideoVigilancia servicio) throws Exception;
    List<ServicioVideoVigilancia> Listar() throws Exception;
    ServicioVideoVigilancia Buscar(int numServicio) throws Exception;
    void altaServicioVigilancia(ServicioVideoVigilancia unServicio, Boolean clienteI, Boolean propiedadI)throws Exception;
    void eliminarServicioVideoVigilancia(ServicioVideoVigilancia unServicio)throws Exception;
    void editarServicioVideoVigilancia(ServicioVideoVigilancia unServicio)throws Exception;
    void DesinstalarDispositivo(ServicioVideoVigilancia servicio) throws Exception;
}
