/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Cliente;
import DataTypes.Cobrador;
import DataTypes.LineaRecibo;
import DataTypes.Servicio;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaRecibo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Geronimo
 */
public class PersistenciaRecibo implements IPersistenciaRecibo{
    private static PersistenciaRecibo _instancia = null;
    private PersistenciaRecibo() { }
    public static PersistenciaRecibo GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaRecibo();
        return _instancia;
    }
    
    
    public void Cobrar(int numero, Cobrador cobrador) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL CobrarRecibo(?, ?) }")) {
           
            consulta.setInt(1, numero);
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
    
        public void GenerarRecibos(HashMap lista) throws Exception{
            try {
                Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
            } catch (Exception ex) {
                System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
            }

            try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                    CallableStatement consulta = conexion.prepareCall("{ CALL GenerarRecibo(?, ?, ?, ?, ?, ?) }")) {
                 
                
                Iterator<Map.Entry<Cliente, List<Servicio>>> iterador = lista.entrySet().iterator();

                while(iterador.hasNext()){
                    
                    
                    Map.Entry<Cliente, List<Servicio>> item = iterador.next();

                    for(Servicio s : item.getValue()){

                    }
                }

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

}
