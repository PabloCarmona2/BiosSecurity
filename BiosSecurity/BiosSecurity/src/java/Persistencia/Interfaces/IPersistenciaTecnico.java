/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Tecnico;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaTecnico {
    Tecnico Buscar(int cedula) throws Exception;
    void Agregar(Tecnico tecnico) throws Exception;
    void Modificar(Tecnico tecnico) throws Exception;
    void Eliminar(Tecnico tecnico) throws Exception;
    List<Tecnico> ListarTecnicos() throws Exception;
}
