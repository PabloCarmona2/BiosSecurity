/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.LineaRecibo;
import DataTypes.Servicio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class PersistenciaLineaRecibo {
    private static PersistenciaLineaRecibo _instancia = null;
    private PersistenciaLineaRecibo() { }
    public static PersistenciaLineaRecibo GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaLineaRecibo();
        return _instancia;
    }
    
    public void RegitrarEnRecibo(LineaRecibo linea, Connection pconexion) throws Exception{
        
        Connection conexion = null;
        CallableStatement consulta = null;
        try{
           
            conexion = pconexion;
           
            consulta = conexion.prepareCall("{ CALL RegistrarLineaEnRecibo(?, ?, ?) }");
                    
            consulta.setDouble(1, linea.getImporte());
            consulta.setInt(2, linea.getServicio().getNumServicio());
            consulta.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(3);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
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
    public List<LineaRecibo> ListarLineas(int numeroRecibo) throws Exception{
        ResultSet resultadoConsulta=null;
        Connection conexion=null;
        PreparedStatement consulta=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new Exception("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
            
            consulta = conexion.prepareStatement("SELECT * FROM LineaRecibo where NumRecibo=?;"); 
            
            consulta.setInt(1, numeroRecibo);
            
            resultadoConsulta = consulta.executeQuery();
            List<LineaRecibo> listaLineas = new ArrayList<LineaRecibo>();
            LineaRecibo linea;
            
            double importe;
            Servicio servicio;
            
            
            while(resultadoConsulta.next()){
                
                importe=resultadoConsulta.getInt("Importe");
                servicio= Persistencia.FabricaPersistencia.getPersistenciaServicioAlarma().Buscar(resultadoConsulta.getInt("Servicio"));
                if (servicio==null) {
                    servicio=null;//buscar servicio video vigilancia
                }
                linea = new LineaRecibo(importe,servicio);
                
                listaLineas.add(linea);
            }
            
            return listaLineas;
            
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
}
