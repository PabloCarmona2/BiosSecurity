/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.Dispositivo;
import Logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
import DataTypes.Tecnico;

/**
 *
 * @author Geronimo
 */
public class ControladorInstalaciones extends Controlador {
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            List<Servicio> servicios = new ArrayList<Servicio>();
            
            if(request.getSession().getAttribute("servicios") == null){
                
                servicios = FabricaLogica.GetLogicaServicio().Listar();
                request.getSession().setAttribute("servicios", servicios);
                
            }else{
                
                servicios = ((List<Servicio>)request.getSession().getAttribute("servicios"));
            
            }
            
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los servicios.", request);
        }
        
        mostrarVista("index", request, response);
    }
    
    public void ver_get(HttpServletRequest request, HttpServletResponse response) {
        int numServicio;
        
        try {
            
            numServicio = Integer.parseInt(request.getParameter("numServicio"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de servicio no es válido.", request);
            
            mostrarVista("ver", request, response);
            
            return;
            
        }
        
        try {
            
            Servicio servicio = (Servicio)FabricaLogica.GetLogicaServicio().Buscar(numServicio);
            
            if (servicio != null) {
                
                request.setAttribute("servicio", servicio);
                
                cargarMensaje("¡Servicio encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún servicio con el numero de servicio " + numServicio + ".", request);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el servicio.", request);
        }
        
        mostrarVista("ver", request, response);
    }
    
    public void instalar_get(HttpServletRequest request, HttpServletResponse response) {
        
        mostrarVista("instalar", request, response);
        
    }
    
    public void instalar_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            if(request.getParameter("numServicio") != null && request.getParameter("dispositivo") != null){
                
               
                
                Servicio servicio = FabricaLogica.GetLogicaServicio().Buscar(Integer.parseInt(request.getParameter("numServicio")));
                
                if(servicio instanceof ServicioAlarma){
                    mostrarVista("rellenarAlarma", request, response);
                }
                else if(servicio instanceof ServicioVideoVigilancia){
                    mostrarVista("rellenarCamara", request, response);
                }else{
                    cargarMensaje("¡ERROR! Tipo de servicio invalido.", request);
                    return;
                }
                
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar instalar el dispositivo.", request);
        }
    }
    
    public void rellenar_post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        
        try{
            Servicio servicio = FabricaLogica.GetLogicaServicio().Buscar(Integer.parseInt(request.getParameter("numServicio")));
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().Buscar(Integer.parseInt(request.getParameter("numInventario")));
            Tecnico tecnico = (Tecnico)FabricaLogica.GetLogicaEmpleado().Buscar(Integer.parseInt(request.getParameter("empleado")));
            
            if(servicio instanceof ServicioAlarma){
                
                Alarma alarma = (Alarma)dispositivo;
                alarma.setDescripcionUbicacion(request.getParameter("descripcion"));
                alarma.setInstalador(tecnico);
                
                
                List<Alarma> alarmasInstaladas = ((ServicioAlarma) servicio).getAlarmas();
                alarmasInstaladas.add((Alarma)dispositivo);
                ((ServicioAlarma) servicio).setAlarmas(alarmasInstaladas);
                
                FabricaLogica.GetLogicaServicio().InstalarDispositivo(servicio);
            
                cargarMensaje("¡Alarma instalado con éxito!", request.getSession());

                response.sendRedirect("instalaciones");
                
                
            }
            else if(servicio instanceof ServicioVideoVigilancia){
                
                Camara camara = (Camara)dispositivo;
                camara.setDescripcionUbicacion(request.getParameter("descripcion"));
                camara.setInstalador(tecnico);
                
                if(request.getParameter("exterior") != null){
                    camara.setExterior(true);
                }else{
                    camara.setExterior(false);
                }
                
                List<Camara> camarasInstaladas = ((ServicioVideoVigilancia) servicio).getCamaras();
                camarasInstaladas.add((Camara)dispositivo);
                ((ServicioVideoVigilancia) servicio).setCamaras(camarasInstaladas);
                
                
                FabricaLogica.GetLogicaServicio().InstalarDispositivo(servicio);
            
                cargarMensaje("¡Camara instalada con éxito!", request.getSession());

                response.sendRedirect("instalaciones");
                
            }else{
                cargarMensaje("¡ERROR! Tipo de servicio invalido.", request);
                return;
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al instalar el dispositivo.", request);
        }
    }
}
