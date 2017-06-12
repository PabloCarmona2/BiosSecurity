/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.ServicioVideoVigilancia;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaAlarma;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta;
        
        try {
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareStatement("SELECT * FROM Alarmas INNER JOIN Dispositivos ON Alarmas.NumInventario = Dispositivo.NumInventario WHERE Dispositivo = ?;");
            
            consulta.setInt(1, numInventario);
            
            resultadoConsulta = consulta.executeQuery();
            
            Alarma alarma = null;
            
            String ubicacion;
            ServicioVideoVigilancia servicio;
            Tecnico tecnico;
            
            if(resultadoConsulta.next()){
                ubicacion = resultadoConsulta.getString("DescripcionUbicacion");
                
                //servicio = Persistencia.Trabajo.PersistenciaServicioVideovigilancia.GetInstancia().Buscar(resultadoConsulta.getInt("Servicio"));
                
                tecnico = Persistencia.Trabajo.PersistenciaTecnico.GetInstancia().Buscar(resultadoConsulta.getInt("Tecnico"));
                
                //camara = new Camara(numInventario, ubicacion, exterior, servicio, tecnico);
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
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AltaAlarma(?, ?, ?, ?) }")) {
           
            consulta.setNull(1, java.sql.Types.VARCHAR);
            consulta.setNull(2, java.sql.Types.INTEGER);
            consulta.setInt(3, java.sql.Types.INTEGER);
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
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL InstalarAlarma(?, ?, ?, ?, ?, ?) }")) {
           
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
    
    
    public void Eliminar(Alarma alarma) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
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
}
