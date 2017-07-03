/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Cliente;
import DataTypes.Cobrador;
import DataTypes.LineaRecibo;
import DataTypes.Precios;
import DataTypes.Recibo;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import Logica.FabricaLogica;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geronimo
 */
public class ControladorRecibos extends Controlador {
    
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        
        verificarLogueo(request, response);
        
        mostrarVista("index", request, response);
    }
    
    public void generar_get(HttpServletRequest request, HttpServletResponse response) {
       try{
            String fecha = new SimpleDateFormat("MMMM").format(new Date());
            request.setAttribute("mes", fecha);
       } catch(Exception ex){
           cargarMensaje("¡ERROR! se produjo un error al mostrar el mes", request);
           
           mostrarVista("index", request, response);
       }
        mostrarVista("generacionRecibos", request, response);
        
    }
    
    public void generar_post(HttpServletRequest request, HttpServletResponse response) {
        
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
                        
                        String rutaRelativa = "/Precios.txt";
                        String rutaAbsoluta = getServletContext().getRealPath(rutaRelativa);
                        
                        Precios precios = FabricaLogica.GetLogicaPrecio().Obtener(rutaAbsoluta);
                        
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
                
                FabricaLogica.GetLogicaRecibo().GenerarRecibos(recibos);

                cargarMensaje("¡Recibos generados con éxito!", request.getSession());

                response.sendRedirect("recibos");
            }else {
                
                cargarMensaje("No hay servicios contratados, por lo tanto no se pueden generar los recibos!.", request);
                return;
                
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar generar los recibos.", request);
        }
    }
    public void cobrar_get(HttpServletRequest request, HttpServletResponse response) {
        
        mostrarVista("cobrarRecibo", request, response);
        
    }
    
    public void cobrar_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            
            Recibo recibo = FabricaLogica.GetLogicaRecibo().Buscar(Integer.parseInt(request.getParameter("numRecibo")));
            
            if(recibo != null){
                
                if(recibo.isCobrado()){
                    
                    cargarMensaje("Este recibo ya fue cobrado!.", request);
                    
                    response.sendRedirect("recibos");
                    
                    return;
                }
                recibo.setCobrado(true);
                
                Cobrador cobrador = (Cobrador)request.getSession().getAttribute("empleadoLogueado");
                
                recibo.setCobrador(cobrador);
                
                FabricaLogica.GetLogicaRecibo().Cobrar(recibo);
                
                cargarMensaje("¡Recibo cobrado con éxito!", request.getSession());

                response.sendRedirect("recibos");
                
            }else {
                
                cargarMensaje("El recibo que desea cobrar no existe!.", request);
                    
                return;
                
            }
        }catch(Exception ex){
            
            cargarMensaje("¡ERROR! Se produjo un error al intentar generar los recibos.", request);
        
        }
    }
    public void recibosacobrar_post(HttpServletRequest request, HttpServletResponse response){
    
        try {
            List<Recibo> recibos = new ArrayList<Recibo>();
            List<String> barrios= new ArrayList<String>();
            
            String barrio= request.getParameter("recibosacobrar");
            
            recibos = FabricaLogica.GetLogicaRecibo().RecibosaCobrar(barrio);
            
                for (Recibo r : recibos){
                    if (!barrios.contains(r.getCliente().getBarrio())) {
                         barrios.add(r.getCliente().getBarrio());   
                    }
                }
                
            barrios= (List<String>)request.getSession().getAttribute("barrios");
            request.getSession().setAttribute("barrios", barrios);
            request.setAttribute("recibos", recibos);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los recibos.", request);
        }
        
        mostrarVista("listarRecibosPorZona", request, response);
    }
    public void recibosacobrar_get(HttpServletRequest request, HttpServletResponse response){
     String barrio="";
        try {
            List<Recibo> recibos = new ArrayList<Recibo>();
            List<String> barrios= new ArrayList<String>();
            
            recibos = FabricaLogica.GetLogicaRecibo().ListarRecibos();
            
                for (Recibo r : recibos){
                    if (!barrios.contains(r.getCliente().getBarrio())) {
                         barrios.add(r.getCliente().getBarrio());   
                    }
                }
                
            request.getSession().setAttribute("barrios", barrios);
            request.setAttribute("recibosGenerales", recibos);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los recibos.", request);
        }
        
        mostrarVista("listarRecibosPorZona", request, response);
    }
     
}
