/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Cliente;
import DataTypes.Servicio;
import Logica.Interfaces.ILogicaCliente;
import static Logica.Trabajo.LogicaPropiedad.Validar;
import Persistencia.FabricaPersistencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class LogicaCliente implements ILogicaCliente{
    private static LogicaCliente _instancia = null;
    private LogicaCliente() { }
    public static LogicaCliente GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaCliente();
        return _instancia;
    }
    private static boolean comprobarTel(String cadena){
	try {
		Integer.parseInt(cadena.trim());
		return true;
	} catch (NumberFormatException ex){
		return false;
        }
    }
    public static void Validar(Cliente cliente) throws Exception
    {
        try{
         
            if(cliente == null){
                throw new Exception("El cliente no puede ser nulo!");
            }
            if(cliente.getNombre().length() > 25 || cliente.getNombre().length() == 0){
                throw new Exception("El nombre del cliente no puede tener mas de 25 caracteres o estar vacío!");
            }
            if(cliente.getBarrio().length() > 25 || cliente.getNombre().length() == 0){
                throw new Exception("El campo barrio del cliente no puede tener mas de 25 caracteres o estar vacío!");
            }
            if(cliente.getDirCobro().length() > 30 || cliente.getNombre().length() == 0){
                throw new Exception("El campo direccion de cobro del cliente no puede tener mas de 30 caracteres o estar vacío!");
            }
            if(!(Logica.Trabajo.LogicaCliente.comprobarTel(cliente.getTelefono()))){
                throw new Exception("No se puede validar este numero de telefono!");
            }           
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }   
    }
    
    public Cliente Buscar(int cedula) throws Exception{
        try{
            return FabricaPersistencia.getPersistenciaCliente().Buscar(cedula);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public List<Cliente> Listar(String criterio) throws Exception{
        
        List<Cliente> clientes = new ArrayList<Cliente>();
            
        try{
            
            if(criterio == null || criterio.length() == 0){
                
                return FabricaPersistencia.getPersistenciaCliente().Listar();
                
            }else{
                
                Cliente cliente = (Cliente)this.Buscar(Integer.parseInt(criterio));
                clientes.add(cliente);
                
                return clientes;
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public HashMap<Cliente, List<Servicio>> ClientesYServiciosOrdenados() throws Exception{
       try{
         
             return FabricaPersistencia.getPersistenciaCliente().ClientesYServiciosOrdenados();
        
       }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Modificar(Cliente cliente) throws Exception{
        try{
         
            Validar(cliente);
        
            Persistencia.FabricaPersistencia.getPersistenciaCliente().Modificar(cliente);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
