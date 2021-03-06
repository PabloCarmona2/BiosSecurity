/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.Cliente;
import DataTypes.Propiedad;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaAlarma;
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
public class PersistenciaAlarma implements IPersistenciaAlarma {
    private static PersistenciaAlarma _instancia = null;
    private PersistenciaAlarma() { }
    public static PersistenciaAlarma GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaAlarma();
        return _instancia;
    }
    
    
    public Alarma Buscar(int numInventario) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta;
        
        try {
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareStatement("SELECT * FROM Alarmas INNER JOIN Dispositivos ON Alarmas.NumInventario = Dispositivos.NumInventario WHERE Alarmas.NumInventario = ?;");
            
            consulta.setInt(1, numInventario);
            
            resultadoConsulta = consulta.executeQuery();
            
            Alarma alarma = null;
            
            String ubicacion;
            Tecnico tecnico;
            
            if(resultadoConsulta.next()){
                ubicacion = resultadoConsulta.getString("DescripcionUbicacion");
                
                tecnico = Persistencia.Trabajo.PersistenciaTecnico.GetInstancia().Buscar(resultadoConsulta.getInt("Tecnico"));
                
                alarma = new Alarma(numInventario, ubicacion, tecnico);
            }
            
            return alarma;
            
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
    
    public void Agregar(Alarma alarma) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AltaAlarma(?, ?, ?, ?) }")) {
           
            consulta.setNull(1, java.sql.Types.VARCHAR);
            consulta.setNull(2, java.sql.Types.INTEGER);
            consulta.setNull(3, java.sql.Types.INTEGER);
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
    
    public void Instalar(Alarma alarma, int numServicio) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL InstalarAlarma(?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, alarma.getNumInventario());
            consulta.setString(2, alarma.getDescripcionUbicacion());
            consulta.setInt(3, numServicio);
            consulta.setInt(4, alarma.getInstalador().getCedula());
            consulta.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(5);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Desinstalar(Alarma alarma, int numServicio) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL DesinstalarAlarma(?,?) }")) {
           //saque el parametro de salida
            consulta.setInt(1, alarma.getNumInventario());
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
    
    
    public void Eliminar(Alarma alarma) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL BajaAlarma(?, ?) }")) {
           
            consulta.setInt(1, alarma.getNumInventario());
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
    
    public List<Alarma> ListarXServicio(int numServicio) throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta;
        
        try {
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareStatement("SELECT * FROM Alarmas INNER JOIN Dispositivos ON Alarmas.NumInventario = Dispositivos.NumInventario WHERE Servicio = ?;");
            
            consulta.setInt(1, numServicio);
            
            resultadoConsulta = consulta.executeQuery();
            
            List<Alarma> alarmas = new ArrayList<Alarma>();
            Alarma alarma = null;
            
            int numInventario;
            String descripcionUbicacion;
            Tecnico tecnico;
            
            while(resultadoConsulta.next()){
                numInventario = resultadoConsulta.getInt("NumInventario");
                descripcionUbicacion = resultadoConsulta.getString("DescripcionUbicacion");
                tecnico = PersistenciaTecnico.GetInstancia().Buscar(resultadoConsulta.getInt("Tecnico"));
                
                alarma = new Alarma(numInventario, descripcionUbicacion, tecnico);
                
                alarmas.add(alarma);
            }
            
            return alarmas;
            
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
    
    public List<Alarma> Listar() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                Statement consulta = conexion.createStatement(); ResultSet resultadoConsulta = consulta.executeQuery("SELECT * FROM Alarmas INNER JOIN Dispositivos ON Alarmas.NumInventario = Dispositivos.NumInventario;")) {
           
            List<Alarma> alarmas = new ArrayList<Alarma>();
            Alarma alarma = null;
            
            while(resultadoConsulta.next()){
                
                alarma = new Alarma();
                
                alarma.setNumInventario(resultadoConsulta.getInt("NumInventario"));
                alarma.setDescripcionUbicacion(resultadoConsulta.getString("DescripcionUbicacion"));
                Tecnico tecnico = PersistenciaTecnico.GetInstancia().Buscar(resultadoConsulta.getInt("Tecnico"));
                alarma.setInstalador(tecnico);
                
                alarmas.add(alarma);
            }
            
            return alarmas;
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
