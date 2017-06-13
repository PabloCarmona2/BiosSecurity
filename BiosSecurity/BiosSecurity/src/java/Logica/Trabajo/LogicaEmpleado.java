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
import java.util.Date;
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
    
    public static void Validar(Empleado empleado) throws Exception
    {
        try{
            
            Date fechaActual = new Date();
            
            if(empleado == null){
                throw new Exception("El empleado no puede ser nulo!");
            }
            if(empleado.getNombre().length() > 25 || empleado.getNombre().length() == 0){
                throw new Exception("El nombre del empleado no puede tener mas de 25 caracteres!");
            }
            if(empleado.getClave().length() > 20 || empleado.getClave().length() <= 1){
                throw new Exception("La clave de ingreso al sistema no puede estar vacia o tener mas de 20 caracteres!");
            }
            if(empleado.getfIngreso().after(fechaActual)){
                throw new Exception("La fecha de ingreso no puede ser posterior a la actual!");
            }
            if(empleado.getSueldo() <= 0){
                throw new Exception("El sueldo del empleado no puede ser menor o igual a 0!");
            }
            
            if(empleado instanceof Tecnico){
                if(((Tecnico) empleado).getEspecializacion().length() != 7){
                    throw new Exception("La longitud del texto ingresado no coincide con las especializaciones disponibles!");
                }
            }else if(empleado instanceof Cobrador){
                //codigo para cobrador (comprobarias el tema del transporte)
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public Empleado Buscar(int cedula) throws Exception {
        
       Empleado emp = null;
       
       emp = Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().Buscar(cedula);
       
       if(emp == null){
           emp = Persistencia.FabricaPersistencia.GetPersistenciaTecnico().Buscar(cedula);
       }
       
       if(emp == null){
           //falta cobrador
       }
       
       return emp; 
    }
    
    public void Agregar(Empleado emp) throws Exception {
        
        Validar(emp);
        
        if (emp instanceof Administrador){
            
            Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().Agregar((Administrador)emp);
        
        }else if(emp instanceof Tecnico){
            
            Persistencia.FabricaPersistencia.GetPersistenciaTecnico().Agregar((Tecnico)emp);
       
        }else if(emp instanceof Cobrador){
            
            //falta cobrador
        
        }
    }
    
    public void Modificar(Empleado emp) throws Exception {
        
        Validar(emp);
        
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
    
    public Empleado LoginEmpleado(int cedula, String clave) throws Exception{
        
       Empleado empleado = null;
        
       empleado = Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().LoginAdministrador(cedula, clave);
      
       if(empleado == null){
           
           empleado = Persistencia.FabricaPersistencia.GetPersistenciaTecnico().LoginTenico(cedula, clave);
       
       }
       
       if(empleado == null){
           
           empleado = Persistencia.FabricaPersistencia.GetPersistenciaCobrador().LoginCobrador(cedula, clave);
       
       }
        
        return empleado;
    }
    
}
