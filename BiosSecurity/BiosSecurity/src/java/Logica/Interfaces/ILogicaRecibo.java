/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Interfaces;

import DataTypes.Cliente;
import DataTypes.Recibo;
import DataTypes.Servicio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface ILogicaRecibo {
    void GenerarRecibos(List<Recibo> recibos) throws Exception;
    void Cobrar(Recibo recibo) throws Exception;
    Recibo Buscar(int numRecibo) throws Exception;
    List<Recibo> RecibosaCobrar(String zona) throws Exception;
    List<Recibo> ListarRecibos() throws Exception;
}
