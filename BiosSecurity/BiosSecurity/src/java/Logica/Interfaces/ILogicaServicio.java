/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Interfaces;

import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface ILogicaServicio {
    void InstalarDispositivo(Servicio servicio) throws Exception;
    List<Servicio> Listar(String criterio) throws Exception;
    Servicio Buscar(int numServicio) throws Exception;
    void altaServicio(Servicio unServicio);
    void EliminarServicio(Servicio unServicio)throws Exception;
    void EditarServicio(Servicio unServicio)throws Exception;
    void DesinstalarDispositivo(Servicio servicio) throws Exception;
}
