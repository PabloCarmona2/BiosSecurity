/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Cobrador;
import DataTypes.Recibo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaRecibo {
    void Cobrar(Recibo recibo) throws Exception;
    List<Recibo> RecibosaCobrar(String zona) throws Exception;
    void GenerarRecibos(List<Recibo> lista) throws Exception;
    Recibo Buscar(int numRecibo) throws Exception;
    List<Recibo> ListarRecibos() throws Exception;
}
