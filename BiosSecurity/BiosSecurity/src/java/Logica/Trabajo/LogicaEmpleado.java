/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Administrador;
import DataTypes.Cobrador;
import DataTypes.Empleado;
import DataTypes.Tecnico;
import Logica.Interfaces.ILogicaEmpleado;
import Persistencia.FabricaPersistencia;
/**
 *
 * @author Geronimo
 */
public class LogicaEmpleado implements ILogicaEmpleado{
    private static LogicaEmpleado _instancia = null;
    
    private LogicaEmpleado() { }
    
    public static LogicaEmpleado GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaEmpleado();
        return _instancia;
    }
    
    public Empleado Buscar(int cedula) throws Exception {
        
       Empleado emp = null;
       
       emp= (Empleado)Persistencia.FabricaPersistencia.GetPersistenciaAdministrador();
       if(emp!=null){
           return emp;
       }else{
           emp=(Empleado)Persistencia.FabricaPersistencia.GetPersistenciaTecnico();
       }
       if(emp!=null){
           return emp;
       }
       else{
           //falta cobrador
       }
       return emp; 
    }
    
    public void Agregar(Empleado emp) throws Exception {
        if (emp instanceof Administrador){
          Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().Agregar((Administrador)emp);
        }else if(emp instanceof Tecnico){
            Persistencia.FabricaPersistencia.GetPersistenciaTecnico().Agregar((Tecnico)emp);
        }else if(emp instanceof Cobrador){
            //falta cobrador
        }
    }
    
    public void Modificar(Empleado emp) throws Exception {
       if (emp instanceof Administrador){
          Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().Modificar((Administrador)emp);
        }else if(emp instanceof Tecnico){
            Persistencia.FabricaPersistencia.GetPersistenciaTecnico().Modificar((Tecnico)emp);
        }else if(emp instanceof Cobrador){
            //falta cobrador
        }
    }
    
    public void Eliminar(Empleado emp) throws Exception {
         if (emp instanceof Administrador){
          Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().Eliminar((Administrador)emp);
        }else if(emp instanceof Tecnico){
            Persistencia.FabricaPersistencia.GetPersistenciaTecnico().Eliminar((Tecnico)emp);
        }else if(emp instanceof Cobrador){
            //falta cobrador
        }
    }
}
