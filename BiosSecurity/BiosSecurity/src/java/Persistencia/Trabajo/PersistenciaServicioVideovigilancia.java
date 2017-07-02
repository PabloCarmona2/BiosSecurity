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
import Persistencia.Interfaces.IPersistenciaServicioVideoVigilancia;
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
public class PersistenciaServicioVideovigilancia implements IPersistenciaServicioVideoVigilancia{
    private static PersistenciaServicioVideovigilancia _instancia = null;
    private PersistenciaServicioVideovigilancia() { }
    public static PersistenciaServicioVideovigilancia GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaServicioVideovigilancia();
        return _instancia;
    }
    
    public ServicioVideoVigilancia Buscar(int numeroServicio)throws Exception{
        
          ServicioVideoVigilancia srvVideo=null;
        
          int numServicio;
          Date fecha;
          boolean monitoreo;
          Propiedad propiedadCliente;
          List<Camara> dispositivos;
          Boolean terminal;
          
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

            consulta = conexion.prepareStatement("SELECT * FROM ServicioVideoVigilancia INNER JOIN Servicios ON ServicioVideoVigilancia.NumServicio = Servicios.NumServicio WHERE ServicioVideoVigilancia.NumServicio = ?;");
            
            consulta.setInt(1, numeroServicio);
            
            resultadoConsulta = consulta.executeQuery();
            
           if(resultadoConsulta.next()){

               numServicio=resultadoConsulta.getInt("NumServicio");
               fecha=resultadoConsulta.getDate("Fecha");
               monitoreo=resultadoConsulta.getBoolean("Monitoreo");
               propiedadCliente=PersistenciaPropiedad.GetInstancia().BuscarXServicio(numServicio);
               dispositivos= PersistenciaCamara.GetInstancia().ListarXServicio(numServicio);
               terminal=resultadoConsulta.getBoolean("Terminal");
               
                
               srvVideo = new ServicioVideoVigilancia(numServicio,fecha,monitoreo,propiedadCliente,dispositivos,terminal);
               
            }
             return srvVideo;
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
    
    public void InstalarDispositivo(ServicioVideoVigilancia servicio) throws Exception{
        try
            {
                if (!servicio.getCamaras().isEmpty())
                {
                    PersistenciaCamara.GetInstancia().Instalar(servicio.getCamaras().get((servicio.getCamaras().size() - 1)), servicio.getNumServicio());
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
    public void DesinstalarDispositivo(ServicioVideoVigilancia servicio) throws Exception{
        try
            {
                if (!servicio.getCamaras().isEmpty())
                {
                    PersistenciaCamara.GetInstancia().Desinstalar(servicio.getCamaras().get((servicio.getCamaras().size() - 1)), servicio.getNumServicio());
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
    
    public List<ServicioVideoVigilancia> ListaXCliente(Cliente cliente) throws Exception{
        
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

            consulta = conexion.prepareCall("{ CALL ServicioCamaraXCliente(?)  }");
            
            consulta.setInt(1, cliente.getCedula());
            
            resultadoConsulta = consulta.executeQuery();
            
            List<ServicioVideoVigilancia> servicios = new ArrayList<ServicioVideoVigilancia>();
            
            ServicioVideoVigilancia servicio = null;
            
            int numServicio;
            Date fecha;
            Boolean monitoreo;
            Propiedad propiedad;
            Boolean terminal;
            
            List<Camara> dispositivos = new ArrayList<Camara>();
            
            while(resultadoConsulta.next()){
                
                numServicio = resultadoConsulta.getInt("NumServicio");
                fecha = resultadoConsulta.getDate("Fecha");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                propiedad = PersistenciaPropiedad.GetInstancia().Buscar(resultadoConsulta.getInt("Propiedad"), cliente.getCedula());
                terminal = resultadoConsulta.getBoolean("Terminal");
                
                dispositivos = PersistenciaCamara.GetInstancia().ListarXServicio(numServicio);
                
                servicio = new ServicioVideoVigilancia(numServicio, fecha, monitoreo, propiedad, dispositivos, terminal);
                
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
    
    public void altaServicioVigilancia(ServicioVideoVigilancia servicio) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL AltaServicioVideo(?, ?, ?, ?, ?) }")) {
           
            consulta.setDate(1, (java.sql.Date)servicio.getFecha());
            consulta.setBoolean(2, servicio.isMonitoreo());
            consulta.setInt(3, servicio.getPropiedadCliente().getIdProp());
            consulta.setInt(4, servicio.getPropiedadCliente().getDueño().getCedula());
            consulta.setBoolean(5, servicio.isTerminal());
     
            consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
            consulta.executeUpdate();
            String error = consulta.getString(7);
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public void eliminarServicioVideoVigilancia(ServicioVideoVigilancia servicio)throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL EliminarServicioVideo(?, ?) }")) {           
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
    
    public void editarServicioVideoVigilancia(ServicioVideoVigilancia unServicio)throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL EditarServicioVideo(?, ?, ?, ?, ?, ?, ?) }")) {
           
            consulta.setInt(1, unServicio.getNumServicio());
            consulta.setDate(2, (java.sql.Date)unServicio.getFecha());
            consulta.setBoolean(3, unServicio.isMonitoreo());
            consulta.setInt(4, unServicio.getPropiedadCliente().getIdProp());
            consulta.setInt(5, unServicio.getPropiedadCliente().getDueño().getCedula());
            consulta.setBoolean(6, unServicio.isTerminal());
            
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
    
    public List<ServicioVideoVigilancia> Listar() throws Exception{
        
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
            
            resultadoConsulta = consulta.executeQuery("SELECT * FROM ServicioVideoVigilancia INNER JOIN Servicios ON ServicioVideoVigilancia.NumServicio = Servicios.NumServicio");
            
            
            List<ServicioVideoVigilancia> servicios = new ArrayList<ServicioVideoVigilancia>();
            
            ServicioVideoVigilancia servicio = null;
            
            int numServicio;
            Date fecha;
            Boolean monitoreo;
            Propiedad propiedad;
            Boolean terminal;
            
            List<Camara> dispositivos = new ArrayList<Camara>();
            
            while(resultadoConsulta.next()){
                
                numServicio = resultadoConsulta.getInt("NumServicio");
                fecha = resultadoConsulta.getDate("Fecha");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                Cliente cliente = PersistenciaCliente.GetInstancia().Buscar(resultadoConsulta.getInt("Cliente"));
                propiedad = PersistenciaPropiedad.GetInstancia().Buscar(resultadoConsulta.getInt("Propiedad"), cliente.getCedula());
                terminal = resultadoConsulta.getBoolean("Terminal");
                
                dispositivos = PersistenciaCamara.GetInstancia().ListarXServicio(numServicio);
                
                servicio = new ServicioVideoVigilancia(numServicio, fecha, monitoreo, propiedad, dispositivos, terminal);
                
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
