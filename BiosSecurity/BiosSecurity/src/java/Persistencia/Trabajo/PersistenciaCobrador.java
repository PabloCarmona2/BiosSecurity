/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;


import DataTypes.Cliente;
import DataTypes.LineaRecibo;
import DataTypes.Recibo;
import DataTypes.Cobrador;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaCobrador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geronimo
 */
public class PersistenciaCobrador implements IPersistenciaCobrador{
    private static PersistenciaCobrador _instancia = null;
    private PersistenciaCobrador() { }
    public static PersistenciaCobrador GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaCobrador();
        return _instancia;
    }
    
    
     public Cobrador Buscar(int cedula)throws Exception{
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
         
            Connection conexion = null;
            PreparedStatement consulta = null;
            ResultSet resultadoConsulta;
            Cobrador cobrador=null;
         try {
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareStatement("SELECT * FROM Cobradores INNER JOIN Empleados ON Cobradores.Cedula = Empleados.Cedula WHERE Cobradores.Cedula = ?;");
            
            consulta.setInt(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            String nombre;
            String clave;
            Date fIngreso;
            double sueldo;
            
             if(resultadoConsulta.next()){
                nombre = resultadoConsulta.getString("Nombre");
                clave = resultadoConsulta.getString("Clave");
                fIngreso = resultadoConsulta.getDate("FIngreso");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                String transporte= resultadoConsulta.getString("Transporte");
               
                
                cobrador = new Cobrador(cedula, nombre, clave, fIngreso, sueldo,transporte);
            }
             return cobrador;
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
    public Cobrador LoginCobrador(int cedula, String clave) throws Exception{
        Cobrador cobrador = null;
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        
           consulta = conexion.prepareStatement("Select * from biossecurity.empleados e inner join biossecurity.cobradores t ON e.Cedula = t.Cedula where t.Cedula = ? and e.Clave = ? AND BajaLogica = false;"); 
           
        
        consulta.setInt(1, cedula);
        consulta.setString(2, clave);
        ResultSet resultado = consulta.executeQuery();
        String nombre;
        String claveAdmin;
        Date fIngreso;
        double sueldo;
        String transporte;
            
        if(resultado.next()){
           nombre = resultado.getString("Nombre");
           claveAdmin = resultado.getString("Clave");
           fIngreso = resultado.getDate("FIngreso");
           sueldo = resultado.getDouble("Sueldo");
           transporte = resultado.getString("Transporte");
                    
           cobrador = new Cobrador(cedula, nombre, claveAdmin, fIngreso, sueldo, transporte);
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
         return cobrador;
    }
    
    public void AgregarCobrador(Cobrador Cob) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AgregarCobrador(?, ?, ?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, Cob.getCedula());
            consulta.setString(2, Cob.getNombre());
            consulta.setString(3, Cob.getClave());
            consulta.setDate(4, new java.sql.Date(Cob.getfIngreso().getTime()));
            consulta.setDouble(5, Cob.getSueldo());
            consulta.setString(6, Cob.getTransporte());
     
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
    
    public void EliminarCobrador(Cobrador Cob) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL EliminarCobrador(?, ?) }")) {
           
            consulta.setInt(1, Cob.getCedula());
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
    
    public void EditarCobrador(Cobrador Cob) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL ModificarCobrador(?, ?, ?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, Cob.getCedula());
            consulta.setString(2, Cob.getNombre());
            consulta.setString(3, Cob.getClave());
            consulta.setDate(4, new java.sql.Date(Cob.getfIngreso().getTime()));
            consulta.setDouble(5, Cob.getSueldo());
            consulta.setString(6, Cob.getTransporte());
            
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
    
    public List<Cobrador> ListarCobradores() throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Cobradores INNER JOIN Empleados ON Cobradores.Cedula = Empleados.Cedula AND Cobradores.BajaLogica = false;"); 
                ResultSet resultadoConsulta = consulta.executeQuery()) {
           
            
            List<Cobrador> cobradores = new ArrayList<Cobrador>();
            Cobrador cob;
            
            int _cedula;
            String nombre;
            String clave;
            Date fIngreso;
            double sueldo;
            String transporte;
            
            while(resultadoConsulta.next()){
                _cedula = resultadoConsulta.getInt("Cedula");
                nombre = resultadoConsulta.getString("Nombre");
                clave = resultadoConsulta.getString("Clave");
                fIngreso = resultadoConsulta.getDate("FIngreso");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                transporte = resultadoConsulta.getString("Transporte");
                
                cob = new Cobrador(_cedula, nombre, clave, fIngreso, sueldo, transporte);
                
                cobradores.add(cob);
            }
            
            return cobradores;
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
}
