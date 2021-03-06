/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Cliente;
import DataTypes.Propiedad;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaPropiedad {
    
    Propiedad Buscar(int id, int cliente) throws Exception;
    void Modificar(Propiedad casa) throws Exception;
    Propiedad BuscarUltimaXCliente(int pCliente) throws Exception;
    List<Propiedad> ListarXCliente(Cliente cliente) throws Exception;
}
