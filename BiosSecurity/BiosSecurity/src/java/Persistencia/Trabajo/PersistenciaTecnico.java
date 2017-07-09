/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaTecnico;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Geronimo
 */
public class PersistenciaTecnico implements IPersistenciaTecnico{
    private static PersistenciaTecnico _instancia = null;
    private PersistenciaTecnico() { }
    public static PersistenciaTecnico GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaTecnico();
        return _instancia;
    }
    
    public Tecnico Buscar(int cedula) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta;
        
        try {
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareStatement("SELECT * FROM Tecnicos INNER JOIN Empleados ON Tecnicos.Cedula = Empleados.Cedula WHERE Tecnicos.Cedula = ?;");
            
            consulta.setInt(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            
            Tecnico tecnico = null;
            
            
            String nombre;
            String clave;
            Date fIngreso;
            double sueldo;
            Boolean eAlarmas;
            Boolean eCamaras;
            
            if(resultadoConsulta.next()){
                nombre = resultadoConsulta.getString("Nombre");
                clave = resultadoConsulta.getString("Clave");
                fIngreso = resultadoConsulta.getDate("FIngreso");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                eAlarmas = resultadoConsulta.getBoolean("Ealarmas");
                eCamaras = resultadoConsulta.getBoolean("Ecamaras");
                
                tecnico = new Tecnico(cedula, nombre, clave, fIngreso, sueldo, eCamaras, eAlarmas);
            }
            
            return tecnico;
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
    
    public void Agregar(Tecnico tecnico) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AltaTecnico(?, ?, ?, ?, ?, ?, ?, ?) }")) {
           
            java.util.Date fecha = tecnico.getfIngreso();
            java.sql.Date fechaIngreso = new java.sql.Date(fecha.getTime());
            
            consulta.setInt(1, tecnico.getCedula());
            consulta.setString(2, tecnico.getNombre());
            consulta.setString(3, tecnico.getClave());
            consulta.setDate(4, fechaIngreso);
            consulta.setDouble(5, tecnico.getSueldo());
            consulta.setBoolean(6, tecnico.geteAlarmas());
            consulta.setBoolean(7, tecnico.geteCamaras());
            consulta.registerOutParameter(8, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(8);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Modificar(Tecnico tecnico) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL ModificarTecnico(?, ?, ?, ?, ?, ?, ?, ?) }")) {
           
            java.util.Date fecha = tecnico.getfIngreso();
            java.sql.Date fechaIngreso = new java.sql.Date(fecha.getTime());
            
            consulta.setInt(1, tecnico.getCedula());
            consulta.setString(2, tecnico.getNombre());
            consulta.setString(3, tecnico.getClave());
            consulta.setDate(4, fechaIngreso);
            consulta.setDouble(5, tecnico.getSueldo());
            consulta.setBoolean(6, tecnico.geteAlarmas());
            consulta.setBoolean(7, tecnico.geteCamaras());
            consulta.registerOutParameter(8, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(8);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Eliminar(Tecnico tecnico) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL BajaTecnico(?, ?) }")) {
           
            consulta.setInt(1, tecnico.getCedula());
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
    
    public List<Tecnico> ListarTecnicos() throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Tecnicos INNER JOIN Empleados ON Tecnicos.Cedula = Empleados.Cedula AND Tecnicos.BajaLogica = false;"); 
                ResultSet resultadoConsulta = consulta.executeQuery()) {
           
            
            List<Tecnico> tecnicos = new ArrayList<Tecnico>();
            Tecnico tecnico;
            
            int _cedula;
            String nombre;
            String clave;
            Date fIngreso;
            double sueldo;
            Boolean eAlarmas;
            Boolean eCamaras;
            
            while(resultadoConsulta.next()){
                _cedula = resultadoConsulta.getInt("Cedula");
                nombre = resultadoConsulta.getString("Nombre");
                clave = resultadoConsulta.getString("Clave");
                fIngreso = resultadoConsulta.getDate("FIngreso");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                eAlarmas = resultadoConsulta.getBoolean("Ealarmas");
                eCamaras = resultadoConsulta.getBoolean("Ecamaras");
                
                tecnico = new Tecnico(_cedula, nombre, clave, fIngreso, sueldo, eCamaras, eAlarmas);
                
                tecnicos.add(tecnico);
            }
            
            return tecnicos;
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public Tecnico LoginTenico(int cedula, String clave) throws Exception{
        
        Tecnico tecnico = null;
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta;
        
         try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        
           consulta = conexion.prepareStatement("Select * from biossecurity.empleados e inner join biossecurity.tecnicos t ON e.Cedula = t.Cedula where t.Cedula = ? and e.Clave = ? AND BajaLogica = false;"); 
           
        
        consulta.setInt(1, cedula);
        consulta.setString(2, clave);
        ResultSet resultado = consulta.executeQuery(); 
        String nombre;
        String claveAdmin;
        Date fIngreso;
        double sueldo;
        Boolean eAlarmas;
        Boolean eCamaras;
            
        if(resultado.next()){
           nombre = resultado.getString("Nombre");
           claveAdmin = resultado.getString("Clave");
           fIngreso = resultado.getDate("FIngreso");
           sueldo = resultado.getDouble("Sueldo");
           eAlarmas = resultado.getBoolean("Ealarmas");
           eCamaras = resultado.getBoolean("Ecamaras");
                    
           tecnico = new Tecnico(cedula, nombre, claveAdmin, fIngreso, sueldo, eCamaras, eAlarmas);
        }
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
         return tecnico;
    }
    
}
