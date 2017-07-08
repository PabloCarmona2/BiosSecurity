/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Cliente;
import DataTypes.ServicioAlarma;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaServicioAlarma {
    void InstalarDispositivo(ServicioAlarma servicio) throws Exception;
    ServicioAlarma Buscar(int numeroServicio)throws Exception;
    List<ServicioAlarma> ListaXCliente(Cliente cliente, Date fecha) throws Exception;
    List<ServicioAlarma> Listar() throws Exception;
    void altaServicioAlarma(ServicioAlarma servicio, Boolean clienteI, Boolean propiedadI)throws Exception;
    void eliminarServicioAlarma(ServicioAlarma servicio)throws Exception;
    void editarServicioAlarma(ServicioAlarma unServicio)throws Exception;
    void DesinstalarDispositivo(ServicioAlarma servicio) throws Exception;
    
}
