/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Cliente;
import DataTypes.Dispositivo;
import DataTypes.Propiedad;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaServicioAlarma;
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
public class PersistenciaServicioAlarma implements IPersistenciaServicioAlarma{
    private static PersistenciaServicioAlarma _instancia = null;
    private PersistenciaServicioAlarma() { }
    public static PersistenciaServicioAlarma GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaServicioAlarma();
        return _instancia;
    }
    
    public void altaServicioAlarma(ServicioAlarma servicio) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AltaServicioAlarma(?, ?, ?, ?, ?, ?) }")) {
            Date fechaJava = servicio.getFecha();
            java.sql.Date fecha = new java.sql.Date(fechaJava.getTime());
            
            consulta.setDate(1, fecha);
            consulta.setBoolean(2, servicio.isMonitoreo());
            consulta.setInt(3, servicio.getPropiedadCliente().getIdProp());
            consulta.setInt(4, servicio.getPropiedadCliente().getDueño().getCedula());
            consulta.setInt(5, servicio.getCodAnulacion());
     
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
    
    public void eliminarServicioAlarma(ServicioAlarma servicio)throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL EliminarServicioAlarma(?, ?) }")) {   
            
            consulta.setInt(1, servicio.getNumServicio());
            consulta.registerOutParameter(2, java.sql.Types.VARCHAR);
            consulta.executeUpdate();           
            String error = consulta.getString(2);
            
            if(error != null){
                throw new Exception("Error: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    public void editarServicioAlarma(ServicioAlarma unServicio)throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL EditarServicioAlarma(?, ?, ?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, unServicio.getNumServicio());
            consulta.setDate(2, (java.sql.Date)unServicio.getFecha());
            consulta.setBoolean(3, unServicio.isMonitoreo());
            consulta.setInt(4, unServicio.getPropiedadCliente().getIdProp());
            consulta.setInt(5, unServicio.getPropiedadCliente().getDueño().getCedula());
            consulta.setInt(6, unServicio.getCodAnulacion());
            
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
    
    public void InstalarDispositivo(ServicioAlarma servicio) throws Exception{
        try
            {
                if (!servicio.getAlarmas().isEmpty())
                {
                    PersistenciaAlarma.GetInstancia().Instalar(servicio.getAlarmas().get((servicio.getAlarmas().size() - 1)), servicio.getNumServicio());
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
    public void DesinstalarDispositivo(ServicioAlarma servicio) throws Exception{
        try
            {
                if (!servicio.getAlarmas().isEmpty())
                {
                    PersistenciaAlarma.GetInstancia().Desinstalar(servicio.getAlarmas().get((servicio.getAlarmas().size() - 1)), servicio.getNumServicio());
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
          List<Alarma> dispositivos;
          int codAnulacion;
          Connection conexion = null;
          PreparedStatement consulta = null;
          ResultSet resultadoConsulta;
         try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
         try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareStatement("SELECT * FROM ServicioAlarmas INNER JOIN Servicios ON ServicioAlarmas.NumServicio = Servicios.NumServicio WHERE ServicioAlarmas.NumServicio = ?;");
            
            consulta.setInt(1, numeroServicio);
            
            resultadoConsulta = consulta.executeQuery();
            
           if(resultadoConsulta.next()){
           numServicio=resultadoConsulta.getInt("NumServicio");
           fecha=resultadoConsulta.getDate("Fecha");
           monitoreo=resultadoConsulta.getBoolean("Monitoreo");
           propiedadCliente= PersistenciaPropiedad.GetInstancia().BuscarXServicio(numServicio);
           dispositivos= PersistenciaAlarma.GetInstancia().ListarXServicio(numeroServicio);
           codAnulacion=resultadoConsulta.getInt("CodAnulacion");
               
                
           srvAlarma = new ServicioAlarma(numServicio,fecha,monitoreo,propiedadCliente,dispositivos,codAnulacion);
            }
             return srvAlarma;
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
    
    public List<ServicioAlarma> ListaXCliente(Cliente cliente) throws Exception{
        
        try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
      
        
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta;
        
        try {
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.prepareCall("{ CALL ServicioAlarmaXCliente(?)  }");
            
            consulta.setInt(1, cliente.getCedula());
            
            resultadoConsulta = consulta.executeQuery();
            
            List<ServicioAlarma> servicios = new ArrayList<ServicioAlarma>();
            
            ServicioAlarma servicio = null;
            
            int numServicio;
            Date fecha;
            Boolean monitoreo;
            Propiedad propiedad;
            int codigoAnulacion;
            
            List<Alarma> dispositivos = new ArrayList<Alarma>();
            
            while(resultadoConsulta.next()){
                
                numServicio = resultadoConsulta.getInt("NumServicio");
                fecha = resultadoConsulta.getDate("Fecha");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                propiedad = PersistenciaPropiedad.GetInstancia().Buscar(resultadoConsulta.getInt("Propiedad"), cliente.getCedula());
                codigoAnulacion = resultadoConsulta.getInt("CodAnulacion");
                
                dispositivos = PersistenciaAlarma.GetInstancia().ListarXServicio(numServicio);
                
                servicio = new ServicioAlarma(numServicio, fecha, monitoreo, propiedad, dispositivos, codigoAnulacion);
                
                servicios.add(servicio);
            }
            
            return servicios;
            
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
    
    public List<ServicioAlarma> Listar() throws Exception{
        
        try  {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
      
        
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");

            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("SELECT * FROM ServicioAlarmas INNER JOIN Servicios ON ServicioAlarmas.NumServicio = Servicios.NumServicio");
            
            List<ServicioAlarma> servicios = new ArrayList<ServicioAlarma>();
            
            ServicioAlarma servicio = null;
            
            int numServicio;
            Date fecha;
            Boolean monitoreo;
            Propiedad propiedad;
            int codigoAnulacion;
            
            List<Alarma> dispositivos = new ArrayList<Alarma>();
            
            while(resultadoConsulta.next()){
                
                numServicio = resultadoConsulta.getInt("NumServicio");
                fecha = resultadoConsulta.getDate("Fecha");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                Cliente cliente = PersistenciaCliente.GetInstancia().Buscar(resultadoConsulta.getInt("Cliente"));
                propiedad = PersistenciaPropiedad.GetInstancia().Buscar(resultadoConsulta.getInt("Propiedad"), cliente.getCedula());
                codigoAnulacion = resultadoConsulta.getInt("CodAnulacion");
                
                dispositivos = PersistenciaAlarma.GetInstancia().ListarXServicio(numServicio);
                
                servicio = new ServicioAlarma(numServicio, fecha, monitoreo, propiedad, dispositivos, codigoAnulacion);
                
                servicios.add(servicio);
            }
            
            return servicios;
            
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
