/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Alarma;
import DataTypes.Camara;
import DataTypes.Cliente;
import DataTypes.Dispositivo;
import DataTypes.Propiedad;
import DataTypes.Servicio;
import DataTypes.ServicioAlarma;
import DataTypes.ServicioVideoVigilancia;
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
public class ControladorServicios extends Controlador {
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
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
            
            List<Servicio> servicios = new ArrayList<Servicio>();
            
            if(request.getParameter("buscar") != null){
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
                Administrador admin = (Administrador)request.getSession().getAttribute("empleadoLogueado");
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
            
            mostrarVista("ver", request, response);
            
            return;
            
        }
        
        try {
            
            Servicio servicio = (Servicio)FabricaLogica.GetLogicaServicio().Buscar(numServicio);
            
            if (servicio != null) {
                
                request.getSession().setAttribute("servicio", servicio);
                
                cargarMensaje("¡Servicio encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún servicio con el numero de servicio " + numServicio + ".", request);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el servicio.", request);
            
        }
        
        mostrarVista("ver", request, response);
    }
    
    public void registrar_get(HttpServletRequest request, HttpServletResponse response) {
        
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
        
        try{
            
            if(request.getParameter("tipo").equalsIgnoreCase("alarma")){
                mostrarVista("RegistrarServicioAlarma", request, response);
            }
            else if(request.getParameter("tipo").equalsIgnoreCase("camara")){
                mostrarVista("RegistrarServicioVideo", request, response);
            }else{
                cargarMensaje("¡ERROR! Tipo de servicio invalido.", request);
                return;
            }
            
        }catch(Exception ex){
            
            cargarMensaje("¡ERROR! se produjo un error al buscar el servicio", request);
            mostrarVista("index", request, response);
        }
    }
    
    public void registrar_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            int cedula = 0;
            Cliente cliente = new Cliente();
            Propiedad propiedad = new Propiedad();
        
            try {

                cedula = Integer.parseInt(request.getParameter("cedulaCliente"));

                if(cedula == 0)
                {
                    cargarMensaje("¡ERROR! La cédula no es válida.", request);

                    mostrarVista("index", request, response);

                    return;
                }

            } catch (NumberFormatException ex) {

                cargarMensaje("¡ERROR! La cédula no es válida.", request);

                mostrarVista("index", request, response);

                return;

            }
            
            if(!(request.getParameter("clienteInexistente").isEmpty())){
                
                String nombre = request.getParameter("nombreCliente");

                String barrio = request.getParameter("barrio");
                
                String direccionCobro = request.getParameter("dirCobro");
                
                String telefono = request.getParameter("telefonoCliente");
                
                
                
                cliente.setCedula(cedula);
                cliente.setNombre(nombre);
                cliente.setBarrio(barrio);
                cliente.setDirCobro(direccionCobro);
                cliente.setTelefono(telefono);
            }else{
                
                
                try {

                    cliente = FabricaLogica.GetLogicaCliente().Buscar(cedula);

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! error al buscar el cliente.", request);

                    mostrarVista("index", request, response);

                    return;

                }
            }
            
            int numeroPropiedad = 0;
            
            try {

                numeroPropiedad = Integer.parseInt(request.getParameter("numPropiedad"));

                if(cedula == 0)
                {
                    cargarMensaje("¡ERROR! El numero de propiedad no es válida.", request);

                    mostrarVista("index", request, response);

                    return;
                }

            } catch (NumberFormatException ex) {

                cargarMensaje("¡ERROR! El numero de propiedad no es válido.", request);

                mostrarVista("index", request, response);

                return;

            }
            
            if(!(request.getParameter("propiedadInexistente").isEmpty())){
                
                String tipo = request.getParameter("tipoPropiedad");

                String direccion = request.getParameter("direccionPropiedad");
                
                
                propiedad.setIdProp(0);
                propiedad.setDireccion(direccion);
                propiedad.setTipo(tipo);
                propiedad.setDueño(cliente);
            }else{
                
                try {

                    propiedad = FabricaLogica.GetLogicaPropiedad().Buscar(numeroPropiedad, cliente.getCedula());

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! error al buscar la propiedad.", request);

                    mostrarVista("index", request, response);

                    return;

                }
            }

            Boolean monitoreo = true;
            
            if(request.getParameter("monitoreo").isEmpty()){
                monitoreo = false;
            }
            
            Boolean terminal = true;
            Integer codigoAnulacion;
            
            if(request.getParameter("tipo") == "camara"){
                
                if(request.getParameter("terminal").isEmpty()){
                    terminal = false;
                }
                
                if(!(request.getParameter("clienteInexistente").isEmpty())){
                    
                    try{
                        
                        FabricaLogica.GetLogicaCliente().Alta(cliente);
                        
                    }catch(Exception ex){
                        throw new Exception("¡ERROR! al dar de alta el cliente!.");
                    }
                    
                }
                
                if(!(request.getParameter("propiedadInexistente").isEmpty())){
                    try{
                        
                        FabricaLogica.GetLogicaPropiedad().Alta(propiedad);
                        
                    }catch(Exception ex){
                        throw new Exception("¡ERROR! al dar de alta la propiedad!.");
                    }
                }
                
                ServicioVideoVigilancia servicio = new ServicioVideoVigilancia();
                servicio.setNumServicio(0);
                servicio.setFecha(new Date());
                servicio.setMonitoreo(monitoreo);
                servicio.setPropiedadCliente(propiedad);
                servicio.setTerminal(terminal);
                servicio.setCamaras(new ArrayList<Camara>());
                
                try{
                    
                    FabricaLogica.GetLogicaServicio().altaServicio(servicio);
                    
                }catch(Exception ex){
                    
                    throw new Exception("¡ERROR! al dar de alta el servicio");
                    
                }
                
            }else{
                
                try {

                    codigoAnulacion = Integer.parseInt(request.getParameter("codAnulacion"));

                    if(cedula == 0)
                    {
                        cargarMensaje("¡ERROR! El codigo de anulacion no es válid0.", request);

                        mostrarVista("index", request, response);

                        return;
                    }

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! El codigo de anulacion no es válido.", request);

                    mostrarVista("index", request, response);

                    return;

                }
                
                if(!(request.getParameter("clienteInexistente").isEmpty())){
                    
                    try{
                        
                        FabricaLogica.GetLogicaCliente().Alta(cliente);
                        
                    }catch(Exception ex){
                        throw new Exception("¡ERROR! al dar de alta el cliente!.");
                    }
                    
                }
                
                if(!(request.getParameter("propiedadInexistente").isEmpty())){
                    try{
                        
                        FabricaLogica.GetLogicaPropiedad().Alta(propiedad);
                        
                    }catch(Exception ex){
                        throw new Exception("¡ERROR! al dar de alta la propiedad!.");
                    }
                }
                
                ServicioAlarma servicio = new ServicioAlarma();
                servicio.setNumServicio(0);
                servicio.setFecha(new Date());
                servicio.setMonitoreo(monitoreo);
                servicio.setPropiedadCliente(propiedad);
                servicio.setCodAnulacion(codigoAnulacion);
                servicio.setAlarmas(new ArrayList<Alarma>());
                
                try{
                    
                    FabricaLogica.GetLogicaServicio().altaServicio(servicio);
                    
                }catch(Exception ex){
                    
                    throw new Exception("¡ERROR! al dar de alta el servicio");
                    
                }
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar instalar el dispositivo.", request);
            mostrarVista("index", request, response);
        }
    }
    
    public void desactivar_get(HttpServletRequest request, HttpServletResponse response) {
        
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
        
        int numServicio;
        
        try {
            
            numServicio = Integer.parseInt(request.getParameter("numServicio"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de servicio no es válido.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
        try {
            
            Servicio servicio = FabricaLogica.GetLogicaServicio().Buscar(numServicio);
            
            if (servicio != null) {
                
                request.getSession().setAttribute("servicio", servicio);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún servicio con el numero de servicio " + numServicio + ".", request);
                mostrarVista("index", request, response);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el servicio a desactivar.", request);
        }
        
        mostrarVista("desactivarServicio", request, response);
        
    }
    
    public void desactivar_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            if(request.getParameter("numServicio") != null){
                
                Servicio servicio = FabricaLogica.GetLogicaServicio().Buscar(Integer.parseInt(request.getParameter("numServicio")));
                
                FabricaLogica.GetLogicaServicio().EliminarServicio(servicio);
                
                cargarMensaje("¡Servicio desactivado con éxito!", request.getSession());
                

                response.sendRedirect("servicios");
                
            }
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar desactivar el servicio.", request);
        }
    }
}
