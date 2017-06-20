/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Interfaces;

import DataTypes.Empleado;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public interface ILogicaEmpleado {
    Empleado Buscar(int cedula) throws Exception;
    void Agregar(Empleado emp) throws Exception;
    void Modificar(Empleado emp) throws Exception;
    void Eliminar(Empleado emp) throws Exception;
    Empleado LoginEmpleado(int cedula, String clave)throws Exception;
    List<Empleado> Listar() throws Exception;
    
}
