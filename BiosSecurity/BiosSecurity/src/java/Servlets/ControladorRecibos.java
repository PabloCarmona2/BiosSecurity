/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Cliente;
import DataTypes.Cobrador;
import DataTypes.Empleado;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
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
        try {
            verificarLogueo(request, response);
            
            Empleado empleado = (Empleado)request.getSession().getAttribute("empleadoLogueado");
            
            if(!(empleado instanceof Administrador) && !(empleado instanceof Cobrador)){
                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al ingresar al sitio.", request);
        }
        
        mostrarVista("index", request, response);
    }
    
    public void generar_get(HttpServletRequest request, HttpServletResponse response) {
       try{
           
           try{
            try{
                Administrador admin = (Administrador)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){

                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");

            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
        }
           
            Date fechaActual = new Date();
            
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaActual);
            
            Integer dia = calendario.get(Calendar.DAY_OF_MONTH);
            Integer mes = calendario.get(Calendar.MONTH) + 1;
            
            Vector<Integer> meses = new Vector<Integer>();
            
            Integer mesParaVector = 1;
            
            while(mesParaVector != mes){
                
                meses.add(mesParaVector);
                mesParaVector++;
                
            }
            
            if(dia > 25){
                meses.add(mes);
            }
            
            request.setAttribute("meses", meses);
            
            mostrarVista("generacionRecibos", request, response);
            
       } catch(Exception ex){
           cargarMensaje("¡ERROR! se produjo un error al mostrar el mes", request);
           
           mostrarVista("index", request, response);
       }
    }
    
    public void generar_post(HttpServletRequest request, HttpServletResponse response) {
        try{

            String rutaAbsoluta = getServletContext().getRealPath("/WEB-INF/precio/Precios.txt");
            Integer mes = Integer.parseInt(request.getParameter("meselejido"));
            
            FabricaLogica.GetLogicaRecibo().GenerarRecibos(rutaAbsoluta, mes);
        
            cargarMensaje("¡Recibos generados con éxito!", request.getSession());

            response.sendRedirect("recibos");
            
           } catch(Exception ex){
               cargarMensaje("¡ERROR! se produjo un error al realizar la accion." + ex.getMessage(), request);

               mostrarVista("index", request, response);
           }
    }
    public void cobrar_get(HttpServletRequest request, HttpServletResponse response) {
        
        
        try{
            try{
                    Cobrador cob = (Cobrador)request.getSession().getAttribute("empleadoLogueado");
                }catch(Exception ex){

                    request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                    response.sendRedirect("login");
            }
            
            if(request.getParameter("numRecibo") == null){
                
                cargarMensaje("No ha seleccionado ningun recibo para cobrar!.", request.getSession());

                response.sendRedirect("recibos");

                return;
                
            }

            } catch(Exception ex){
               cargarMensaje("¡ERROR! se produjo un error al mostrar el sitio", request);

               mostrarVista("index", request, response);
            }
            request.getSession().setAttribute("numRecibo", request.getParameter("numRecibo"));
            mostrarVista("cobrarRecibo", request, response);
    }
    
    public void cobrar_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            
            Recibo recibo = FabricaLogica.GetLogicaRecibo().Buscar(Integer.parseInt((String)request.getSession().getAttribute("numRecibo")));
            request.getSession().removeAttribute("numRecibo");
            
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

                response.sendRedirect("recibos?accion=recibosacobrar");
                
            } else {
                
                cargarMensaje("El recibo que desea cobrar no existe!.", request);
                    
                return;
                
            }
        }catch(Exception ex){
            
            cargarMensaje("¡ERROR! Se produjo un error al intentar generar los recibos." + ex.getMessage(), request);
        
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
            request.setAttribute("barrios", barrios);
            request.setAttribute("recibos", recibos);
            request.setAttribute("zona",barrio);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los recibos." + ex.getMessage(), request);
        }
        
        mostrarVista("listarRecibosPorZona", request, response);
    }
    public void recibosacobrar_get(HttpServletRequest request, HttpServletResponse response){
        try{
            try{
                Cobrador cob = (Cobrador)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){

                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");

            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
        }
        
        
        request.setAttribute("zona", "");
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
            request.setAttribute("recibos", recibos);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los recibos.", request);
        }
        
        mostrarVista("listarRecibosPorZona", request, response);
    }
     
}
