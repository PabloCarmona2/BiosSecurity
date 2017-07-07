/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Interfaces;

import DataTypes.Cliente;
import DataTypes.Propiedad;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface ILogicaPropiedad {
     void Modificar(Propiedad propiedad) throws Exception;
     Propiedad Buscar(int numero, int cliente) throws Exception;
     Propiedad BuscarUltimaXCliente(int pCliente) throws Exception;
     List<Propiedad> ListarXCliente(Cliente cliente) throws Exception;
}
