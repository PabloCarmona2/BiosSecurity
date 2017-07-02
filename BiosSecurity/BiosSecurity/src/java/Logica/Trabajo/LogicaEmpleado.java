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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                if(((Cobrador)empleado).getTransporte().length() > 20){
                    throw new Exception("La longitud del texto referido al transporte no puede superar los 20 caracteres");
                }
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
           emp = Persistencia.FabricaPersistencia.getPersistenciaCobrador().Buscar(cedula);
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
            
            Persistencia.FabricaPersistencia.GetPersistenciaCobrador().AgregarCobrador((Cobrador)emp);
            
        }
    }
    
    public void Modificar(Empleado emp) throws Exception {
        
        Validar(emp);
        
        if (emp instanceof Administrador){
            
          Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().Modificar((Administrador)emp);
        
        }else if(emp instanceof Tecnico){
           
            Persistencia.FabricaPersistencia.GetPersistenciaTecnico().Modificar((Tecnico)emp);
        
        }else if(emp instanceof Cobrador){
           
            Persistencia.FabricaPersistencia.GetPersistenciaCobrador().EditarCobrador((Cobrador)emp);
       
        }
    }
    
    public void Eliminar(Empleado emp) throws Exception {
        
        if (emp instanceof Administrador){
            
          Persistencia.FabricaPersistencia.GetPersistenciaAdministrador().Eliminar((Administrador)emp);
        
        }else if(emp instanceof Tecnico){
           
            Persistencia.FabricaPersistencia.GetPersistenciaTecnico().Eliminar((Tecnico)emp);
        
        }else if(emp instanceof Cobrador){
           
            Persistencia.FabricaPersistencia.GetPersistenciaCobrador().EliminarCobrador((Cobrador)emp);
        
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
    
    public List<Empleado> Listar(String tipo, String criterio) throws Exception {
        try{
            
            
            List<Empleado> empleados = new ArrayList<Empleado>();
            List<Tecnico> tecnicos = new ArrayList<Tecnico>();
            List<Cobrador> cobradores = new ArrayList<Cobrador>();
            List<Administrador> administradores = new ArrayList<Administrador>();
            
            if(criterio == null || criterio.length() == 0){
                
                if(tipo.equalsIgnoreCase("admin")){

                    administradores = FabricaPersistencia.GetPersistenciaAdministrador().ListarAdministrador();
                    empleados.addAll(administradores);

                }else if(tipo.equalsIgnoreCase("tecnico")){

                    tecnicos = FabricaPersistencia.GetPersistenciaTecnico().ListarTecnicos();
                    empleados.addAll(tecnicos);
                }
                else if(tipo.equalsIgnoreCase("cobrador")){

                    //cobradores = FabricaPersistencia.getPersistenciaCobrador().ListarCobradores();
                     empleados.addAll(cobradores);

                }

                return empleados;
                
            }else{
                
                if(tipo.equalsIgnoreCase("admin")){
                    
                    Administrador admin = (Administrador)this.Buscar(Integer.parseInt(criterio));
                    administradores.add(admin);
                    empleados.addAll(administradores);

                }else if(tipo.equalsIgnoreCase("tecnico")){
                    
                    Tecnico tec = (Tecnico)this.Buscar(Integer.parseInt(criterio));
                    tecnicos.add(tec);
                    empleados.addAll(tecnicos);
                }
                else if(tipo.equalsIgnoreCase("cobrador")){

                    Cobrador cob = (Cobrador)this.Buscar(Integer.parseInt(criterio));
                    cobradores.add(cob);
                    empleados.addAll(cobradores);

                }
                
                return empleados;
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
}
