/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Interfaces;

import DataTypes.Dispositivo;

/**
 *
 * @author Geronimo
 */
public interface ILogicaDispositivo {
    Dispositivo Buscar(int numInventario) throws Exception;
    void Agregar(Dispositivo dispositivo) throws Exception;
    void Eliminar(Dispositivo dispositivo) throws Exception;
}
