/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Cobrador;
import DataTypes.Empleado;
import DataTypes.Precios;
import Logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geronimo
 */
public class ControladorPrecios extends Controlador {
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        
            verificarLogueo(request, response);
        
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
        
        mostrarVista("index", request, response);
        
    }
    
    public void setear_post(HttpServletRequest request, HttpServletResponse response) {
        
        double  baseAlarmas = 0;
        
        try {
            
            baseAlarmas = Double.parseDouble(request.getParameter("baseAlarmas"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! Precio base de alarmas no valido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
         double  baseCamaras = 0;
        
        try {
            
            baseCamaras = Double.parseDouble(request.getParameter("baseCamaras"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! Precio base de camaras no valido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
        double  adicionalCamara = 0;
        
        try {
            
            adicionalCamara = Double.parseDouble(request.getParameter("adicionalCamara"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! Precio adicional por camara no valido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
        double  adicionalAlarma = 0;
        
        try {
            
            adicionalAlarma = Double.parseDouble(request.getParameter("adicionalAlarma"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! Precio adicional por alarma no valido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
        int  monitoreoAlarmas = 0;
        
        try {
            
            monitoreoAlarmas = Integer.parseInt(request.getParameter("monitoreoAlarmas"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! Precio por monitoreo de camaras no valido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
        int  monitoreoCamaras = 0;
        
        try {
            
            monitoreoCamaras = Integer.parseInt(request.getParameter("monitoreoCamaras"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! Precio por monitoreo de camaras no valido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        String rutaRelativa = "/WEB-INF/precio/Precios.txt";
        String rutaAbsoluta = getServletContext().getRealPath(rutaRelativa);
        
        Precios precios = new Precios(baseAlarmas, baseCamaras, adicionalAlarma, adicionalCamara, monitoreoAlarmas, monitoreoCamaras);
        
        try {
            
            FabricaLogica.GetLogicaPrecio().Actualizar(precios, rutaAbsoluta);
            
            cargarMensaje("¡Precios actualizados con éxito!", request.getSession());
            
            response.sendRedirect("precios");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el administrador.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
        }
    }
}
