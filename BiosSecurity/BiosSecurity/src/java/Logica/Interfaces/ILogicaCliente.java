/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Interfaces;

import DataTypes.Cliente;
import DataTypes.Servicio;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface ILogicaCliente {
    Cliente Buscar(int cedula) throws Exception;
    void Modificar(Cliente cliente) throws Exception;
    List<Cliente> Listar(String criterio) throws Exception;
}
