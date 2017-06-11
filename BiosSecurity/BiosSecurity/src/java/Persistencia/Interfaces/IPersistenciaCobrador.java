/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Cobrador;
import DataTypes.Recibo;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaCobrador {
    List<Recibo> RecibosaCobrar(String zona) throws Exception;
    Cobrador Buscar(int cedula) throws Exception;
}
