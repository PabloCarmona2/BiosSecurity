/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Cliente;
import DataTypes.LineaRecibo;
import DataTypes.Recibo;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import Logica.FabricaLogica;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        mostrarVista("index", request, response);
    }
    
    public void generar_get(HttpServletRequest request, HttpServletResponse response) {
        
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
                        
                        String texto;
                        FileReader fr = new FileReader("\\Precios.txt");
                        BufferedReader br = new BufferedReader(fr);
                        Recibo recibo;
                        

                        int renglon = 1;
                        String error = null;

                        double precioAlarmas = 0;
                        double precioCamaras = 0;
                        double precioXAlarma = 0;
                        double precioXCamara = 0;
                        double porcentajeMonitoreoCamara = 0;
                        double porcentajeMonitoreoAlarma = 0;

                        while((texto = br.readLine()) != null){

                            int posicionDePeso;
                            posicionDePeso = texto.indexOf("$");

                            int posicionDePorcentaje;
                            posicionDePorcentaje = texto.indexOf(")");

                            switch(renglon){
                                case 1:

                                    precioAlarmas = Double.parseDouble(texto.substring(posicionDePeso - 1));

                                    renglon++;

                                    break;
                                case 2:

                                    precioCamaras = Double.parseDouble(texto.substring(posicionDePeso - 1));

                                    renglon++;

                                    break;
                                case 3:

                                    precioXAlarma = Double.parseDouble(texto.substring(posicionDePeso - 1));

                                    renglon++;

                                    break;
                                case 4:

                                    precioXCamara = Double.parseDouble(texto.substring(posicionDePeso - 1));

                                    renglon++;

                                    break;
                                case 5:

                                    porcentajeMonitoreoCamara = Double.parseDouble(texto.substring(posicionDePorcentaje - 1));

                                    renglon++;

                                    break;
                                case 6:

                                    porcentajeMonitoreoAlarma = Double.parseDouble(texto.substring(posicionDePorcentaje - 1));

                                    renglon++;

                                    break;    
                            }
                        }
                        
                        importe += (s instanceof ServicioAlarma? precioAlarmas : precioCamaras);
                        
                        int cantidadDispositivos = (s instanceof ServicioAlarma? ((ServicioAlarma)s).getAlarmas().size() : ((ServicioVideoVigilancia)s).getCamaras().size());
                        
                        importe += (s instanceof ServicioAlarma? (cantidadDispositivos * precioXAlarma) : (cantidadDispositivos * precioXCamara));
                        
                        importe += (s instanceof ServicioAlarma? (importe * Double.parseDouble("0." + porcentajeMonitoreoAlarma)) : (importe * Double.parseDouble("0." + porcentajeMonitoreoCamara)));
                        
                        
                        linea.setImporte(importe);
                        linea.setServicio(s);
                        
                        lineas.add(linea);
                    }
                    
                    cabezal.setLineas(lineas);
                    
                    FabricaLogica.GetLogicaRecibo().GenerarRecibos(recibos);

                    cargarMensaje("¡Recibos generados con éxito!", request.getSession());

                    response.sendRedirect("recibos");

                }
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
    
}
