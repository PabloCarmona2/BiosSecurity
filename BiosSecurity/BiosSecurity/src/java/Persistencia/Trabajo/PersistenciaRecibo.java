/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Alarma;
import DataTypes.Cliente;
import DataTypes.Cobrador;
import DataTypes.Dispositivo;
import DataTypes.LineaRecibo;
import DataTypes.Recibo;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.Tecnico;
import Persistencia.Interfaces.IPersistenciaRecibo;
import java.io.BufferedReader;
import java.io.FileReader;
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
    
    public List<Recibo> RecibosaCobrar(String zona) throws Exception{
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception ex){
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        try(Connection conexion= DriverManager.getConnection("jdbc:mysql//localhost:3306/BiosSecurity,root,root");
            PreparedStatement consulta=conexion.prepareCall
            ("SELECT * FROM CabezaldeRecibo INNERJOIN Clientes ON CabezaldeRecibo.Cliente = Clientes.Cedula Where Cliente.Barrio=?;");
            ResultSet resultado= consulta.executeQuery()){

            consulta.setString(1, zona);
            
            List<Recibo> listaRecibo = new ArrayList<Recibo>();
            Recibo unRecibo=null;
            
            int numRecibo;
            Date fecha;
            double total;
            Cliente cliente;
            Cobrador cobrador;
            boolean cobrado;
            List<LineaRecibo> lineas;
            
            while(resultado.next()){
                numRecibo = resultado.getInt("NumRecibo");
                fecha = resultado.getDate("Fecha");
                total = resultado.getDouble("Total");
                cliente =Persistencia.FabricaPersistencia.getPersistenciaCliente().Buscar(resultado.getInt("Cliente"));
                cobrador =Persistencia.FabricaPersistencia.getPersistenciaCobrador().Buscar(resultado.getInt("Cobrador"));
                cobrado = resultado.getBoolean("Cobrado");
                lineas= Persistencia.Trabajo.PersistenciaLineaRecibo.GetInstancia().ListarLineas(resultado.getInt("NumRecibo"));
                unRecibo = new Recibo(numRecibo,fecha,total,cliente,cobrador,cobrado,lineas);
                
                listaRecibo.add(unRecibo);
            }
            
            
             return listaRecibo;
        } catch (Exception ex) {
            
          throw new Exception(ex.getMessage());
 
        }
       
    }
    
    public void Cobrar(Recibo recibo) throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un error al instanciar el driver de MySQL.");
        }
        
        try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BiosSecurity", "root", "root");
                CallableStatement consulta = conexion.prepareCall("{ CALL CobrarRecibo(?, ?, ?) }")) {
           
            consulta.setInt(1, recibo.getNumRecibo());
            consulta.setInt(2, recibo.getCobrador().getCedula());
            consulta.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            consulta.executeUpdate();
            
            String error = consulta.getString(2);
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
        public void GenerarRecibos(ArrayList<Recibo> lista) throws Exception{
            Connection conexion = null;
            CallableStatement consulta = null;
        
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/EjemploJDBC", "root", "root");
            
                consulta = conexion.prepareCall("{ CALL GenerarCabezalRecibo(?, ?, ?, ?, ?, ?, ?) }");
            
                conexion.setAutoCommit(false);
                
                String error = null;
                
                for(Recibo r : lista){
                
                    int numeroRecibo;
                    Date fechaJava = r.getFecha();
                    java.sql.Date fecha = new java.sql.Date(fechaJava.getTime());

                    consulta.setDate(1, fecha);
                    consulta.setDouble(2, r.getTotal());
                    consulta.setInt(3, r.getCliente().getCedula());
                    consulta.setNull(4, java.sql.Types.INTEGER);
                    consulta.setBoolean(5, false);
                    consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
                    consulta.registerOutParameter(7, java.sql.Types.INTEGER);

                    error = consulta.getString(6);

                    if(error != null){
                        throw new Exception("ERROR: " + error);
                    }

                    numeroRecibo = consulta.getInt(7);

                    consulta.executeUpdate();

                    for(LineaRecibo l : r.getLineas()){

                        PersistenciaLineaRecibo.GetInstancia().RegitrarEnRecibo(l, numeroRecibo);
                    }
            }
            
            conexion.commit();
            
            if(error != null){
                throw new Exception("ERROR: " + error);
            }

            }catch(Exception ex){

                conexion.rollback();
                throw new Exception(ex.getMessage());

            }finally {

                try {
                    if (consulta != null) {
                        consulta.close();
                    }

                    if (conexion != null) {
                        conexion.close();
                        conexion.setAutoCommit(true);
                    }
                } catch (Exception ex) {
                    throw new Exception("¡ERROR! Ocurrió un error al cerrar los recursos.");
                }
            }
        }

}
