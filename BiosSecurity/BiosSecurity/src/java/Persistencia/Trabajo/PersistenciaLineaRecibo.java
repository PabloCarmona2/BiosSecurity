/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.LineaRecibo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

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
    
    public void RegitrarEnRecibo(LineaRecibo linea, int numRecibo) throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL RegistrarEnRecibo(?, ?, ?, ?) }")) {
           
            consulta.setDouble(1, linea.getImporte());
            consulta.setInt(2, numRecibo);
            consulta.setInt(3, linea.getServicio().getNumServicio());
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
}
