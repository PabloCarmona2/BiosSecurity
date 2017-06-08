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
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Tecnicos INNER JOIN Empleados ON Tecnicos.Empleado = Empleados.Cedula WHERE Empleado = ?;"); ResultSet resultadoConsulta = consulta.executeQuery()) {
           
            consulta.setInt(1, cedula);
            
            Tecnico tecnico = null;
            
            
            String nombre;
            String clave;
            Date fIngreso;
            double sueldo;
            String especializacion;
            
            if(resultadoConsulta.next()){
                nombre = resultadoConsulta.getString("Nombre");
                clave = resultadoConsulta.getString("Clave");
                fIngreso = resultadoConsulta.getDate("FIngreso");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                especializacion = resultadoConsulta.getString("Especializacion");
                
                tecnico = new Tecnico(cedula, nombre, clave, fIngreso, sueldo, especializacion);
            }
            
            return tecnico;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Agregar(Tecnico tecnico) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AgregarTecnico(?, ?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, tecnico.getCedula());
            consulta.setString(2, tecnico.getNombre());
            consulta.setString(3, tecnico.getClave());
            consulta.setDate(4, (java.sql.Date)tecnico.getfIngreso());
            consulta.setDouble(5, tecnico.getSueldo());
            consulta.setString(6, tecnico.getEspecializacion());
            consulta.registerOutParameter(7, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(7);
            
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
                CallableStatement consulta = conexion.prepareCall("{ CALL ModificarTecnico(?, ?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, tecnico.getCedula());
            consulta.setString(2, tecnico.getNombre());
            consulta.setString(3, tecnico.getClave());
            consulta.setDate(4, (java.sql.Date)tecnico.getfIngreso());
            consulta.setDouble(5, tecnico.getSueldo());
            consulta.setString(6, tecnico.getEspecializacion());
            consulta.registerOutParameter(7, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(7);
            
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
                CallableStatement consulta = conexion.prepareCall("{ CALL EliminarTecnico(?, ?) }")) {
           
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
                PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Tecnicos INNER JOIN Empleados ON Tecnicos.Empleado = Empleados.Cedula;"); 
                ResultSet resultadoConsulta = consulta.executeQuery()) {
           
            
            List<Tecnico> tecnicos = new ArrayList<Tecnico>();
            Tecnico tecnico;
            
            int _cedula;
            String nombre;
            String clave;
            Date fIngreso;
            double sueldo;
            String especializacion;
            
            while(resultadoConsulta.next()){
                _cedula = resultadoConsulta.getInt("Cedula");
                nombre = resultadoConsulta.getString("Nombre");
                clave = resultadoConsulta.getString("Clave");
                fIngreso = resultadoConsulta.getDate("FIngreso");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                especializacion = resultadoConsulta.getString("Especializacion");
                
                tecnico = new Tecnico(_cedula, nombre, clave, fIngreso, sueldo, especializacion);
                
                tecnicos.add(tecnico);
            }
            
            return tecnicos;
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
}
