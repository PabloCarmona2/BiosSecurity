/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Dispositivo;
import DataTypes.Propiedad;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import Persistencia.Interfaces.IPersistenciaServicioAlarma;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class PersistenciaServicioAlarma implements IPersistenciaServicioAlarma{
    private static PersistenciaServicioAlarma _instancia = null;
    private PersistenciaServicioAlarma() { }
    public static PersistenciaServicioAlarma GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaServicioAlarma();
        return _instancia;
    }
    
    public void InstalarDispositivo(ServicioAlarma servicio) throws Exception{
        try
            {
                if (!servicio.getDispositivos().isEmpty())
                {
                    PersistenciaAlarma.GetInstancia().Instalar((Alarma)servicio.getDispositivos().get((servicio.getDispositivos().size() - 1)), servicio.getNumServicio());
                }
                else
                {
                    throw new Exception("No hay dispositivos para instalar.");
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.getMessage());
            }
    }
    public ServicioAlarma Buscar(int numeroServicio)throws Exception{
        
          ServicioAlarma srvAlarma=null;
        
          int numServicio;
          Date fecha;
          boolean monitoreo;
          Propiedad propiedadCliente;
          List<Dispositivo> dispositivos;
          int codAnulacion;
         try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
      
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
        PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM ServicioAlarmas WHERE NumServicio = ?;"); ResultSet resultadoConsulta = consulta.executeQuery()) {
        
            consulta.setInt(1, numeroServicio);
            
           if(resultadoConsulta.next()){
           numServicio=resultadoConsulta.getInt("NumServicio");
           fecha=resultadoConsulta.getDate("Fecha");
           monitoreo=resultadoConsulta.getBoolean("Monitoreo");
           propiedadCliente=null;//buscar propiedad cliente
           dispositivos=null;//listar dispositivos
           codAnulacion=resultadoConsulta.getInt("CodAnulacion");
               
                
           srvAlarma = new ServicioAlarma(numServicio,fecha,monitoreo,propiedadCliente,dispositivos,codAnulacion);
            }
             return srvAlarma;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
     }
    
}
