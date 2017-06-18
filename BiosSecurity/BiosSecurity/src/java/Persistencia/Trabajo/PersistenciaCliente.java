/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Cliente;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaCliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.CallableStatement;

/**
 *
 * @author Geronimo
 */
public class PersistenciaCliente implements IPersistenciaCliente{
    private static PersistenciaCliente _instancia = null;
    private PersistenciaCliente() { }
    public static PersistenciaCliente GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaCliente();
        return _instancia;
    }
    
    public Cliente Buscar(int cedula)throws Exception{

          Connection conexion = null;
          PreparedStatement consulta = null;
          ResultSet resultadoConsulta;
          Cliente cli=null;
        
         try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
      
       try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareStatement("SELECT * FROM Clientes WHERE Cedula = ?;");
            
            consulta.setInt(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
             String nombre;
             String barrio;
             String dirCobro;
             String telefono;
            
             if(resultadoConsulta.next()){
                nombre = resultadoConsulta.getString("Nombre");
                barrio = resultadoConsulta.getString("Barrio");
                dirCobro = resultadoConsulta.getString("DirCobro");
                telefono = resultadoConsulta.getString("Telefono");
               
                
                cli = new Cliente(cedula, nombre, barrio, dirCobro, telefono);
            }
             return cli;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }finally {

            try {
                if (consulta != null) {
                    consulta.close();
                }

                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception ex) {
                throw new Exception("¡ERROR! Ocurrió un error al cerrar los recursos.");
            }
        }
    }
    
    public List<Cliente> Listar()throws Exception{
        
        try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Clientes;"); ResultSet resultadoConsulta = consulta.executeQuery()) {
            
            List<Cliente> clientes = new ArrayList<Cliente>();
            Cliente cliente;
             
             int cedula;
             String nombre;
             String barrio;
             String dirCobro;
             String telefono;
            
             while(resultadoConsulta.next()){
                cedula =  resultadoConsulta.getInt("Cedula");
                nombre = resultadoConsulta.getString("Nombre");
                barrio = resultadoConsulta.getString("Barrio");
                dirCobro = resultadoConsulta.getString("DirCobro");
                telefono = resultadoConsulta.getString("Telefono");
               
                
                cliente = new Cliente(cedula, nombre, barrio, dirCobro, telefono);
                
                clientes.add(cliente);
            }
             return clientes;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public HashMap<Cliente, List<Servicio>> ClientesYServiciosOrdenados() throws Exception{
        try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta;
        
        List<Cliente> clientes = null;
        
        HashMap<Cliente, List<Servicio>> coleccion = new HashMap<Cliente, List<Servicio>>();
        
        
        try {
            
            clientes = this.Listar();
            
            for(Cliente c : clientes){
                
                List<ServicioAlarma> serviciosA = PersistenciaServicioAlarma.GetInstancia().ListaXCliente(c);
                List<ServicioVideoVigilancia> serviciosV = PersistenciaServicioVideovigilancia.GetInstancia().ListaXCliente(c);
                
                List<Servicio> servicios = new ArrayList<Servicio>();
                
                for(ServicioAlarma s : serviciosA){
                    servicios.add(s);
                }
                
                for(ServicioVideoVigilancia s : serviciosV){
                    servicios.add(s);
                }
                
                coleccion.put(c, servicios);
                
            }
            
            
            return coleccion;
            
        }catch(Exception ex){

                throw new Exception(ex.getMessage());

        }finally {

            try {
                if (consulta != null) {
                    consulta.close();
                }

                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception ex) {
                throw new Exception("¡ERROR! Ocurrió un error al cerrar los recursos.");
            }
        }
        
    }
}
