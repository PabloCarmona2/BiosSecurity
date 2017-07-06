/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Cliente;
import DataTypes.Propiedad;
import Persistencia.Interfaces.IPersistenciaPropiedad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Geronimo
 */
public class PersistenciaPropiedad implements IPersistenciaPropiedad{
    private static PersistenciaPropiedad _instancia = null;
    private PersistenciaPropiedad() { }
    public static PersistenciaPropiedad GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaPropiedad();
        return _instancia;
    }
    
    public Propiedad Buscar(int id, int pCliente) throws Exception{
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

            consulta = conexion.prepareStatement("SELECT * FROM Propiedades WHERE IdProp = ? AND Cliente = ?;");
            
            consulta.setInt(1, id);
            consulta.setInt(2, pCliente);
            
            resultadoConsulta = consulta.executeQuery();
            
            Propiedad propiedad = null;
            
            String tipo;
            String direccion;
            Cliente cliente;
            
            if(resultadoConsulta.next()){
                tipo = resultadoConsulta.getString("Tipo");
                direccion = resultadoConsulta.getString("Direccion");
                
                cliente = PersistenciaCliente.GetInstancia().Buscar(pCliente);
                
                propiedad = new Propiedad(id, tipo, direccion, cliente);
            }
            
            return propiedad;
            
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
    
    public Propiedad BuscarUltimaXCliente(int pCliente) throws Exception{
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

            consulta = conexion.prepareStatement("SELECT * FROM Propiedades WHERE Cliente = ? ORDER BY IdProp desc limit 1;");
            
            consulta.setInt(1, pCliente);
            
            resultadoConsulta = consulta.executeQuery();
            
            Propiedad propiedad = null;
            Integer id;
            String tipo;
            String direccion;
            Cliente cliente;
            
            if(resultadoConsulta.next()){
                id = resultadoConsulta.getInt("IdProp");
                tipo = resultadoConsulta.getString("Tipo");
                direccion = resultadoConsulta.getString("Direccion");
                
                cliente = PersistenciaCliente.GetInstancia().Buscar(pCliente);
                
                propiedad = new Propiedad(id, tipo, direccion, cliente);
            }
            
            return propiedad;
            
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
    
    public Propiedad BuscarXServicio(int numServicio) throws Exception{
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

            consulta = conexion.prepareStatement("SELECT * FROM Servicios INNER JOIN propiedades ON Servicios.Propiedad = Propiedades.IdProp AND Servicios.Cliente = Propiedades.Cliente WHERE Servicios.NumServicio = ?;");
            
            consulta.setInt(1, numServicio);
            
            resultadoConsulta = consulta.executeQuery();
            
            Propiedad propiedad = null;
            
            int id;
            String tipo;
            String direccion;
            Cliente cliente;
            
            if(resultadoConsulta.next()){
                id = resultadoConsulta.getInt("Propiedad");
                tipo = resultadoConsulta.getString("Tipo");
                direccion = resultadoConsulta.getString("Direccion");
                
                cliente = PersistenciaCliente.GetInstancia().Buscar(resultadoConsulta.getInt("Cliente"));
                
                propiedad = new Propiedad(id, tipo, direccion, cliente);
            }
            
            return propiedad;
            
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
    public void Modificar(Propiedad casa) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL ModificarPropiedad(?, ?, ?, ?) }")) {
           
            consulta.setInt(1, casa.getIdProp());
            consulta.setString(2, casa.getTipo());
            consulta.setString(3, casa.getDireccion());
            consulta.registerOutParameter(4, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(4);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public int Alta(Propiedad casa, Connection pconexion) throws Exception{
        Connection conexion = null;
        CallableStatement consulta = null;
        try{
            
            Integer numPropiedad = 0;
           
            conexion = pconexion;
            consulta = conexion.prepareCall("{ CALL AltaPropiedad(?, ?, ?, ?, ?) }");
            
            consulta.setString(1, casa.getTipo());
            consulta.setString(2, casa.getDireccion());
            consulta.setInt(3, casa.getDueño().getCedula());
            consulta.registerOutParameter(4, java.sql.Types.VARCHAR);
            consulta.registerOutParameter(5, java.sql.Types.BIGINT);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(4);
            numPropiedad = consulta.getInt(5);
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
            return numPropiedad;
            
        }catch(Exception ex){
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (Exception exR) {
                throw new Exception(exR.getMessage());
            }

            throw new Exception(ex.getMessage());

        }finally {

            try {
                if (consulta != null) {
                    consulta.close();
                } 
            } catch (Exception ex) {
                throw new Exception("¡ERROR! Ocurrió un error al cerrar los recursos.");
            }
        }
    }
}
