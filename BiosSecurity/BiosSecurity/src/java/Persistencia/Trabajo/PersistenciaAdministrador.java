/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Administrador;
import Persistencia.Interfaces.IPersistenciaAdministrador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geronimo
 */
public class PersistenciaAdministrador implements IPersistenciaAdministrador {
    private static PersistenciaAdministrador _instancia = null;
    private PersistenciaAdministrador() { }
    
    public static PersistenciaAdministrador GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaAdministrador();
        return _instancia;
    }
    
    public Administrador Buscar(int cedula)throws Exception{
        
          Administrador admin=null;
        
         try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
      
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Administradores WHERE cedula = ?;"); ResultSet resultadoConsulta = consulta.executeQuery()) {
        
            consulta.setInt(1, cedula);
            String nombre;
            String clave;
            Date fIngreso;
            double sueldo;
            
             if(resultadoConsulta.next()){
                nombre = resultadoConsulta.getString("Nombre");
                clave = resultadoConsulta.getString("Clave");
                fIngreso = resultadoConsulta.getDate("FIngreso");
                sueldo = resultadoConsulta.getDouble("Sueldo");
               
                
                admin = new Administrador(cedula, nombre, clave, fIngreso, sueldo);
            }
             return admin;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Agregar(Administrador admin) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AgregarAdministrador(?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, admin.getCedula());
            consulta.setString(2, admin.getNombre());
            consulta.setString(3, admin.getClave());
            consulta.setDate(4, (java.sql.Date)admin.getfIngreso());
            consulta.setDouble(5, admin.getSueldo());
     
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(7);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    public void Modificar(Administrador admin) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL ModificarAdministrador(?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, admin.getCedula());
            consulta.setString(2, admin.getNombre());
            consulta.setString(3, admin.getClave());
            consulta.setDate(4, (java.sql.Date)admin.getfIngreso());
            consulta.setDouble(5, admin.getSueldo());
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(6);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    public void Eliminar(Administrador admin) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL EliminarAdministrador(?, ?) }")) {
           
            consulta.setInt(1, admin.getCedula());
            consulta.registerOutParameter(2, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(2);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public Administrador LoginAdministrador(int cedula, String clave){
        Administrador admin = null;
        
         try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        PreparedStatement consulta = conexion.prepareStatement("Select * from biossecurity.empleados e inner join biossecurity.administradores a where a.Cedula = ? and e.Clave = ?;"); ResultSet resultado = consulta.executeQuery()) {
        
        consulta.setInt(1, cedula);
        consulta.setString(2, clave);
        String nombre;
        String claveAdmin;
        Date fIngreso;
        double sueldo;
            
        if(resultado.next()){
           nombre = resultado.getString("Nombre");
           claveAdmin = resultado.getString("Clave");
           fIngreso = resultado.getDate("FIngreso");
           sueldo = resultado.getDouble("Sueldo");
                    
           admin = new Administrador(cedula, nombre, claveAdmin, fIngreso, sueldo);
        }
        }catch(Exception ex){
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception ex1) {
                Logger.getLogger(PersistenciaAdministrador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
         return admin;
    }
}
