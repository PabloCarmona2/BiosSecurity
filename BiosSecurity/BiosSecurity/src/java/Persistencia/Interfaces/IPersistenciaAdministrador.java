/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Administrador;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaAdministrador {
    Administrador Buscar(int cedula) throws Exception;
    void Agregar(Administrador admin) throws Exception;
    void Modificar(Administrador admin) throws Exception;
    void Eliminar(Administrador admin) throws Exception;
    Administrador LoginAdministrador(int cedula, String clave) throws Exception;
   
}
