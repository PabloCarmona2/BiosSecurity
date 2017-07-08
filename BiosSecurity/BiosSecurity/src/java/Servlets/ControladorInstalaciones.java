/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.Cobrador;
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
            verificarLogueo(request, response);
            
            try{
                try{
                    Tecnico tec = (Tecnico)request.getSession().getAttribute("empleadoLogueado");
                }catch(Exception ex){

                    request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                    response.sendRedirect("login");

                }
            }catch(Exception ex){
                cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
            }
            
            List<Servicio> servicios = new ArrayList<Servicio>();
            
            if(request.getParameter("buscar") != null && request.getParameter("buscar") != ""){
                try {
            
                    Integer.parseInt(request.getParameter("buscar"));

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! El numero de servicio ingresado no es válido.", request);

                    mostrarVista("index", request, response);

                    return;

                }
            }
            
            servicios = FabricaLogica.GetLogicaServicio().Listar(request.getParameter("buscar"));
            
            if(servicios.isEmpty()){
                
                servicios = FabricaLogica.GetLogicaServicio().Listar("");
                request.getSession().setAttribute("servicios", servicios);
                
            }else{
                
                request.getSession().setAttribute("servicios", servicios);
            
            }
            
            
            cargarMensaje("Servicios encontrados exitosamente, cantidad de servicios = " + servicios.size() + ".", request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los servicios.", request);
        }
        
        mostrarVista("index", request, response);
    }
    
    public void ver_get(HttpServletRequest request, HttpServletResponse response) {
        try{
            try{
                Tecnico tec = (Tecnico)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){

                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");

            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
        }
        
        int numServicio;
        
        try {
            
            numServicio = Integer.parseInt(request.getParameter("numServicio"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de servicio no es válido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
        try {
            
            Servicio servicio = (Servicio)FabricaLogica.GetLogicaServicio().Buscar(numServicio);
            
            if (servicio != null) {
                
                request.getSession().setAttribute("servicio", servicio);
                
                cargarMensaje("¡Servicio encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún servicio con el numero de servicio " + numServicio + ".", request);
                
                mostrarVista("index", request, response);
                
                return;
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el servicio.", request);
            
            mostrarVista("index", request, response);
        }
        
        mostrarVista("ver", request, response);
    }
    
    public void instalar_get(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            try{
                Tecnico tec = (Tecnico)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){

                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");

            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
        }
        
        try{
            
            Servicio servicio = FabricaLogica.GetLogicaServicio().Buscar(Integer.parseInt(request.getParameter("numServicio")));
            
            if(servicio instanceof ServicioAlarma){
                mostrarVista("instalarAlarma", request, response);
            }
            else if(servicio instanceof ServicioVideoVigilancia){
                mostrarVista("instalarCamara", request, response);
            }else{
                cargarMensaje("¡ERROR! Tipo de servicio invalido.", request);
                return;
            }
            
        }catch(Exception ex){
            
            cargarMensaje("¡ERROR! se produjo un error al buscar el servicio", request);
            mostrarVista("index", request, response);
        }
        
        mostrarVista("instalar", request, response);
        
    }
    
    public void instalar_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            if(request.getParameter("numServicio") != null && request.getParameter("dispositivo") != null){
                
                Servicio servicio = FabricaLogica.GetLogicaServicio().Buscar(Integer.parseInt(request.getParameter("numServicio")));
                
                try{
                    
                    Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().Buscar(Integer.parseInt(request.getParameter("numInventario")));
                    Tecnico tecnico = (Tecnico)request.getSession().getAttribute("empleadoLogueado");

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
                
//                
                
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar instalar el dispositivo.", request);
        }
    }
    
    
    
    public void desinstalar_get(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            try{
                Tecnico tec = (Tecnico)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){

                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");

            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
        }
        
        int numInventario;
        
        try {
            
            numInventario = Integer.parseInt(request.getParameter("dispositivo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de inventario no es válido.", request);
            
            mostrarVista("ver", request, response);
            
            return;
            
        }
        
        try {
            
            Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().Buscar(numInventario);
            
            if (dispositivo != null) {
                
                request.getSession().setAttribute("dispositivo", dispositivo);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún dispositivo con el numero de inventario " + numInventario + ".", request);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el dispositivo a desinstalar.", request);
        }
        
        mostrarVista("desinstalar", request, response);
        
    }
    
    public void desinstalar_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            if(request.getParameter("numServicio") != null && request.getParameter("dispositivo") != null){
                
                Servicio servicio = FabricaLogica.GetLogicaServicio().Buscar(Integer.parseInt(request.getParameter("numServicio")));
                Dispositivo dispositivo = FabricaLogica.GetLogicaDispositivo().Buscar(Integer.parseInt(request.getParameter("dispositivo")));
                
                if(servicio instanceof ServicioAlarma){
                    ((ServicioAlarma)servicio).setAlarmas(null);
                    List<Alarma> alarmas = new ArrayList<Alarma>();
                    alarmas.add((Alarma)dispositivo);
                    
                    ((ServicioAlarma)servicio).setAlarmas(alarmas);
                }else{
                    ((ServicioVideoVigilancia)servicio).setCamaras(null);
                    List<Camara> camaras = new ArrayList<Camara>();
                    camaras.add((Camara)dispositivo);
                    
                    ((ServicioVideoVigilancia)servicio).setCamaras(camaras);
                }
                
                FabricaLogica.GetLogicaServicio().DesinstalarDispositivo(servicio);
                
                cargarMensaje("¡Dispositivo desinstalado con éxito!", request.getSession());
                

                response.sendRedirect("instalaciones");
                
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar instalar el dispositivo.", request);
        }
    }
}
