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
        
          Cobrador cobrador=null;
          try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Cobradores WHERE cedula = ?;"); ResultSet resultadoConsulta = consulta.executeQuery()) {
        
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
                String transporte= resultadoConsulta.getString("Transporte");
               
                
                cobrador = new Cobrador(cedula, nombre, clave, fIngreso, sueldo,transporte);
            }
             return cobrador;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
     }
    public Cobrador LoginCobrador(int cedula, String clave){
        Cobrador cobrador = null;
        
         try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        PreparedStatement consulta = conexion.prepareStatement("Select * from biossecurity.empleados e inner join biossecurity.cobradores c where c.Cedula = ? and e.Clave = ?;"); ResultSet resultado = consulta.executeQuery()) {
        
        consulta.setInt(1, cedula);
        consulta.setString(2, clave);
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
        }catch(Exception ex){
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception ex1) {
                Logger.getLogger(PersistenciaAdministrador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
         return cobrador;
    }
}
