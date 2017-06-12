/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Camara;
import DataTypes.ServicioVideoVigilancia;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaCamara;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Geronimo
 */
public class PersistenciaCamara implements IPersistenciaCamara{
    private static PersistenciaCamara _instancia = null;
    private PersistenciaCamara() { }
    public static PersistenciaCamara GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaCamara();
        return _instancia;
    }
    
    public Camara Buscar(int numInventario) throws Exception{
        
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

            consulta = conexion.prepareStatement("SELECT * FROM Camaras INNER JOIN Dispositivos ON Camaras.NumInventario = Dispositivo.NumInventario WHERE NumInventario = ? AND BajaLogica = 0;");
            
            consulta.setInt(1, numInventario);
            
            resultadoConsulta = consulta.executeQuery();
            
            Camara camara = null;
            
            String ubicacion;
            Boolean exterior;
            ServicioVideoVigilancia servicio;
            Tecnico tecnico;
            
            if(resultadoConsulta.next()){
                ubicacion = resultadoConsulta.getString("DescripcionUbicacion");
                exterior = resultadoConsulta.getBoolean("Exterior");
                
                //servicio = Persistencia.Trabajo.PersistenciaServicioVideovigilancia.GetInstancia().Buscar(resultadoConsulta.getInt("Servicio"));
                
                tecnico = Persistencia.Trabajo.PersistenciaTecnico.GetInstancia().Buscar(resultadoConsulta.getInt("Tecnico"));
                
                //camara = new Camara(numInventario, ubicacion, exterior, servicio, tecnico);
            }
            
            return camara;
            
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
    
    public void Agregar(Camara camara) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AltaCamara(?, ?, ?, ?, ?) }")) {
           
            consulta.setNull(1, java.sql.Types.VARCHAR);
            consulta.setNull(2, java.sql.Types.BOOLEAN);
            consulta.setNull(3, java.sql.Types.INTEGER);
            consulta.setInt(4, java.sql.Types.INTEGER);
            consulta.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(4);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Instalar(Camara camara, int numServicio) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL InstalarCamara(?, ?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, camara.getNumInventario());
            consulta.setString(2, camara.getDescripcionUbicacion());
            consulta.setBoolean(3, camara.isExterior());
            consulta.setInt(4, numServicio);
            consulta.setInt(5, camara.getInstalador().getCedula());
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
    
    public void Eliminar(Camara camara) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL BajaCamara(?, ?) }")) {
           
            consulta.setInt(1, camara.getNumInventario());
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