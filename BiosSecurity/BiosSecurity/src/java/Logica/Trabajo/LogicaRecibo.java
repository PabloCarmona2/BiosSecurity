/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Cliente;
import DataTypes.LineaRecibo;
import DataTypes.Precios;
import DataTypes.Recibo;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import Logica.FabricaLogica;
import Logica.Interfaces.ILogicaRecibo;
import Persistencia.FabricaPersistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class LogicaRecibo implements ILogicaRecibo{
    
    private static LogicaRecibo _instancia = null;
    
    private LogicaRecibo() { }
    
    public static LogicaRecibo GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaRecibo();
        return _instancia;
    }
    
    public static void Validar(Recibo recibo) throws Exception
    {
        try{
            if (recibo == null)
            {
                throw new Exception("El servicio no puede ser nulo.");
            }
            if(recibo.getCliente() == null){
                throw new Exception("El recibo debe pertenecer a algun cliente.");
            }
            if(recibo.getLineas() == null){
                throw new Exception("No se puede cobrar un recibo que no tiene lineas, su suma total seria siempre 0!.");
            }
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public Recibo Buscar(int numRecibo) throws Exception{
        try{
            
            return FabricaPersistencia.GetPersistenciaRecibo().Buscar(numRecibo);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void GenerarRecibos(String rutaPrecios) throws Exception{
            try{
            
            HashMap<Cliente, List<Servicio>> coleccion = FabricaLogica.GetLogicaCliente().ClientesYServiciosOrdenados();
            
            List<Recibo> recibos = new ArrayList<Recibo>();
            
            Date hoy = new Date();
            
            if(coleccion != null){
                
                Iterator<HashMap.Entry<Cliente, List<Servicio>>> registros = coleccion.entrySet().iterator();
                
                while (registros.hasNext()) {


                    HashMap.Entry<Cliente, List<Servicio>> registro = registros.next();
                    
                    double total = 0;
                    
                    Recibo cabezal = new Recibo();
                    
                    cabezal.setCliente(registro.getKey());
                    cabezal.setCobrado(false);
                    cabezal.setCobrador(null);
                    cabezal.setFecha(hoy);
                    cabezal.setNumRecibo(null);
                    cabezal.setTotal(0);
                    cabezal.setLineas(new ArrayList<LineaRecibo>());
                    
                    
                    List<LineaRecibo> lineas = new ArrayList<LineaRecibo>();
                    
                    
                    for(Servicio s : registro.getValue()){
                        double importe = 0;
                        
                        LineaRecibo linea = new LineaRecibo();
                        
                        linea.setImporte(0);
                        
                        Precios precios = FabricaLogica.GetLogicaPrecio().Obtener(rutaPrecios);
                        
                        importe += (s instanceof ServicioAlarma? precios.getBaseAlarmas() : precios.getBaseCamaras());
                        
                        int cantidadDispositivos = (s instanceof ServicioAlarma? ((ServicioAlarma)s).getAlarmas().size() : ((ServicioVideoVigilancia)s).getCamaras().size());
                        
                        importe += (s instanceof ServicioAlarma? (cantidadDispositivos * precios.getAdicionalAlarma()) : (cantidadDispositivos * precios.getAdicionalCamara()));
                        
                        importe += (s instanceof ServicioAlarma? (importe * Double.parseDouble("0." + precios.getTasaAlarmas())) : (importe * Double.parseDouble("0." + precios.getTasaCamaras())));
                        
                        total += importe;
                        
                        linea.setImporte(importe);
                        linea.setServicio(s);
                        
                        lineas.add(linea);
                        
                    }
                    
                    cabezal.setTotal(total);
                    cabezal.setLineas(lineas);

                    recibos.add(cabezal);
                }
                
                FabricaPersistencia.GetPersistenciaRecibo().GenerarRecibos(recibos);

                
            }else {
                
                throw new Exception("No hay servicios contratados, por lo tanto no se pueden generar los recibos!.");
                
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Cobrar(Recibo recibo) throws Exception{
        try{
            
            Validar(recibo);
            
            FabricaPersistencia.GetPersistenciaRecibo().Cobrar(recibo);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    public List<Recibo> RecibosaCobrar(String zona) throws Exception{
        
        try {
        List<Recibo>recibos=new ArrayList<Recibo>();
        
        recibos=FabricaPersistencia.GetPersistenciaRecibo().RecibosaCobrar(zona);
        
        return recibos;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    public List<Recibo> ListarRecibos() throws Exception{
        
        try {
        List<Recibo>recibos=new ArrayList<Recibo>();
        
        recibos=FabricaPersistencia.GetPersistenciaRecibo().ListarRecibos();
        
        return recibos;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
