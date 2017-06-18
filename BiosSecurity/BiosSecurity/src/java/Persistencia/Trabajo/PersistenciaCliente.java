/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Cliente;
import Persistencia.Interfaces.IPersistenciaCliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

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
}
