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
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Camaras INNER JOIN Dispositivos ON Camaras.Dispositivo = Dispositivo.NumInventario WHERE Dispositivo = ?;"); ResultSet resultadoConsulta = consulta.executeQuery()) {
           
            consulta.setInt(1, numInventario);
            
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
        }
    }
    
    public void Agregar(Camara camara) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AgregarCamara(?, ?, ?, ?, ?) }")) {
           
            consulta.setString(1, camara.getDescripcionUbicacion());
            consulta.setBoolean(2, null);
            consulta.setInt(3, camara.getServicio().getNumServicio());
            consulta.setInt(4, camara.getInstalador().getCedula());
            consulta.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(7);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
consulta.setString(1, camara.getDescripcionUbicacion());
            consulta.setBoolean(2, camara.isExterior());
            consulta.setInt(3, camara.getServicio().getNumServicio());
            consulta.setInt(4, camara.getInstalador().getCedula());
            consulta.registerOutParameter(5, java.sql.Types.VARCHAR);