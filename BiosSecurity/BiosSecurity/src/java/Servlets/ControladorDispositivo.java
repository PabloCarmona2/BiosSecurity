/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.Dispositivo;
import DataTypes.Empleado;
import DataTypes.Tecnico;
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
public class ControladorDispositivo extends Controlador {
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            verificarLogueo(request, response);
            
            List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
            
            if(request.getParameter("buscar") != null){
                try {
            
                    Integer.parseInt(request.getParameter("buscar"));

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! El numero de inventario ingresado no es válido.", request);

                    mostrarVista("index", request, response);

                    return;

                }
            }
            
            
            dispositivos = FabricaLogica.GetLogicaDispositivo().Listar(request.getParameter("buscar"));
            
            if(dispositivos.isEmpty()){
                
                dispositivos = FabricaLogica.GetLogicaDispositivo().Listar(request.getParameter(""));
                request.getSession().setAttribute("dispositivos", dispositivos);
                
            }else{
                
                request.getSession().setAttribute("dispositivos", dispositivos);
            
            }
            
            cargarMensaje("Cantidad de dispositivos: " + dispositivos.size(), request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los dispositivos.", request);
        }
        
        mostrarVista("index", request, response);
    }
    
    public void ver_get(HttpServletRequest request, HttpServletResponse response) {
        int numInventario;
        
        try {
            
            numInventario = Integer.parseInt(request.getParameter("numInventario"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de inventario no es válido.", request);
            
            mostrarVista("ver", request, response);
            
            return;
        }
        
        try {
            
            Dispositivo dispositivo = (Dispositivo)FabricaLogica.GetLogicaDispositivo().Buscar(numInventario);
            
            if (dispositivo != null) {
                
                request.setAttribute("dispositivo", dispositivo);
                
                cargarMensaje("¡Dispositivo encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún dispositivo con el numero de inventario " + numInventario + ".", request);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el dispositivo.", request);
        }
        
        mostrarVista("ver", request, response);
    }
    
    public void agregar_get(HttpServletRequest request, HttpServletResponse response) {
        
        mostrarVista("agregar", request, response);
        
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response) {
        
        
        Boolean camara = false;
        
        
        try{
            if(request.getParameter("tipoDispositivo") != null){
                camara = true;
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al determinar el tipo de dispositivo!.", request);
        }
        
        Dispositivo dispositivo = null;
        
        if(camara){
            dispositivo = new Camara(null, null, null, null);
        }
        else{
            dispositivo = new Alarma(null, null, null);
        }
        
        try {
            
            FabricaLogica.GetLogicaDispositivo().Agregar(dispositivo);
            
            cargarMensaje("¡Dispositivo agregado con éxito!", request.getSession());
            response.sendRedirect("dispositivos");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el dispositivo.", request);
            
            mostrarVista("agregar", request, response);
            
        }
    }
    
    public void eliminar_get(HttpServletRequest request, HttpServletResponse response) {
        Integer numInventario;
        
        try {
            
            
            
            numInventario = Integer.parseInt(request.getParameter("numInventario"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de inventario no es válido.", request);
            
            mostrarVista("eliminar", request, response);
            
            return;
            
        }
        
        try {
            
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().Buscar(numInventario);
            
            if (dispositivo != null) {
                
                request.setAttribute("dispositivo", dispositivo);
                cargarMensaje("¡Dispositivo encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún dispositivo con el numero de inventario " + numInventario + ".", request);
                
            }
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al buscar el dispositivo.", request);
            
        }
        
        mostrarVista("eliminar", request, response);
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) {
        Integer numInventario;
        
        try {
            
            numInventario = Integer.parseInt(request.getParameter("numInventario"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de inventario no es válido.", request);
            
            mostrarVista("eliminar", request, response);
            
            return;
            
        }
        
        try {
            
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().Buscar(Integer.parseInt(request.getParameter("numInventario")));
            
            FabricaLogica.GetLogicaDispositivo().Eliminar(dispositivo);
            
            cargarMensaje("¡Dispositivo eliminado con éxito!", request.getSession());
            
            response.sendRedirect("dispositivos");
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el dispositivo.", request);
            
            mostrarVista("eliminar", request, response);
        }
    }
}
