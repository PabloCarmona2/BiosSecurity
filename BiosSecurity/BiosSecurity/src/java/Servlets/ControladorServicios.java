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
    
    public void registrarservicio_get(HttpServletRequest request, HttpServletResponse response) {
        
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
        
        try {
            
            if((String)request.getSession().getAttribute("tipo") == "alarma"){
                
                mostrarVista("RegistrarServicioAlarma", request, response);
                
            }
            else if((String)request.getSession().getAttribute("tipo") == "camara"){
                
                mostrarVista("RegistrarServicioVideo", request, response);
                
            }else{
                cargarMensaje("¡ERROR! Tipo de servicio invalido.", request);
                mostrarVista("index", request, response);
                return;
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el servicio a desactivar.", request);
        }
        
        mostrarVista("desactivarServicio", request, response);
        
    }
    
    public void registrarservicio_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            Cliente cliente = new Cliente();
            Propiedad propiedad = new Propiedad();
            
            cliente = (Cliente)request.getSession().getAttribute("cliente");
            request.getSession().removeAttribute("cliente");
            
            propiedad = (Propiedad)request.getSession().getAttribute("propiedad");
            request.getSession().removeAttribute("propiedad");

            Boolean monitoreo = true;
            
            if(request.getParameter("monitoreo") == null){
                monitoreo = false;
            }
            Boolean clienteI = false;
            Boolean propiedadI = false;
            Boolean terminal = true;
            Integer codigoAnulacion;
            
            if(request.getSession().getAttribute("tipo") == "camara"){
                
                if(request.getParameter("terminal") == null){
                    terminal = false;
                }
                
                if(request.getSession().getAttribute("clienteI") != null){
                    
                    clienteI = true;
                    request.getSession().removeAttribute("clienteI");
                }
                
                if(request.getSession().getAttribute("propiedadI") != null){
                    
                    propiedadI = true;
                    request.getSession().removeAttribute("propiedadI");
                }
                
                ServicioVideoVigilancia servicio = new ServicioVideoVigilancia();
                servicio.setNumServicio(0);
                servicio.setFecha(new Date());
                servicio.setMonitoreo(monitoreo);
                servicio.setPropiedadCliente(propiedad);
                servicio.setTerminal(terminal);
                servicio.setCamaras(new ArrayList<Camara>());
                
                try{
                    
                    FabricaLogica.GetLogicaServicio().altaServicio(servicio, clienteI, propiedadI);
                    
                }catch(Exception ex){
                    
                    throw new Exception("¡ERROR! al dar de alta el servicio");
                    
                }
                
            }else{
                
                try {

                    codigoAnulacion = Integer.parseInt(request.getParameter("codAnulacion"));

                    if(codigoAnulacion == 0)
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
                
                if(request.getSession().getAttribute("clienteI") != null){
                    
                    clienteI = true;
                    request.getSession().removeAttribute("clienteI");
                }
                
                if(request.getSession().getAttribute("propiedadI") != null){
                    
                    propiedadI = true;
                    request.getSession().removeAttribute("propiedadI");
                }
                
                ServicioAlarma servicio = new ServicioAlarma();
                servicio.setNumServicio(0);
                servicio.setFecha(new Date());
                servicio.setMonitoreo(monitoreo);
                servicio.setPropiedadCliente(propiedad);
                servicio.setCodAnulacion(codigoAnulacion);
                servicio.setAlarmas(new ArrayList<Alarma>());
                
                try{
                    
                    FabricaLogica.GetLogicaServicio().altaServicio(servicio, clienteI, propiedadI);
                    
                }catch(Exception ex){
                    
                    throw new Exception("¡ERROR! al dar de alta el servicio");
                    
                }
            }
            
            cargarMensaje("¡Servicio registrado con éxito!", request.getSession());

            response.sendRedirect("servicios");
            
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
    
    public void procesarcliente_get(HttpServletRequest request, HttpServletResponse response) {
        
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
            if(request.getParameter("tipo") == null){
                cargarMensaje("No selecciono ningun tipo de servicio!.", request.getSession());
                response.sendRedirect("servicios");
                return;
            }
            else{
                if(request.getParameter("tipo").equalsIgnoreCase("alarma")){
                    mostrarVista("procesoCliente", request, response);
                    request.getSession().setAttribute("tipo", "alarma");
                }
                else if(request.getParameter("tipo").equalsIgnoreCase("camara")){
                    mostrarVista("procesoCliente", request, response);
                    request.getSession().setAttribute("tipo", "camara");
                }else{
                    cargarMensaje("¡ERROR! Tipo de servicio invalido.", request);
                    mostrarVista("index", request, response);
                    return;
                }
            }
            
        }catch(Exception ex){
            
            cargarMensaje("¡ERROR! se produjo un error al intentar realizar la accion!.", request);
            mostrarVista("index", request, response);
        }
    }
    
    public void procesarcliente_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            int cedula = 0;
            Cliente cliente = new Cliente();
        
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
            
            if(request.getParameter("clienteInexistente") != null){
                
                request.getSession().setAttribute("clienteI", request.getParameter("clienteInexistente"));
                
                String nombre = request.getParameter("nombreCliente");

                String barrio = request.getParameter("barrioCliente");
                
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
            
            request.getSession().setAttribute("cliente", cliente);
            
            cargarMensaje("¡Cliente seleccionado con éxito!", request.getSession());

            response.sendRedirect("servicios?accion=procesarpropiedad");
            
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar instalar el dispositivo.", request);
            mostrarVista("index", request, response);
        }
    }
    
    public void procesarpropiedad_get(HttpServletRequest request, HttpServletResponse response) {
        
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
            if(request.getSession().getAttribute("cliente") == null){
                cargarMensaje("No selecciono ningun cliente!.", request.getSession());
                response.sendRedirect("servicios");
                return;
            }
            else{
                mostrarVista("procesoPropiedad", request, response);
            }
            
        }catch(Exception ex){
            
            cargarMensaje("¡ERROR! se produjo un error al intentar realizar la accion!.", request);
            mostrarVista("index", request, response);
        }
    }
    
    public void procesarpropiedad_post(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            Propiedad propiedad = new Propiedad();
            Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
            
            int numeroPropiedad = 0;
            
            try {

                numeroPropiedad = Integer.parseInt(request.getParameter("numPropiedad"));

                if(numeroPropiedad == 0)
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
            
            if(request.getParameter("propiedadInexistente") != null){
                
                request.getSession().setAttribute("propiedadI", request.getParameter("propiedadInexistente"));
                
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
            
            
            request.getSession().setAttribute("propiedad", propiedad);
            
            cargarMensaje("¡Propiedad seleccionada con éxito!", request.getSession());
            
            response.sendRedirect("servicios?accion=registrarservicio");
            
        }catch(Exception ex){
            cargarMensaje("¡ERROR! Se produjo un error al intentar instalar el dispositivo.", request);
            mostrarVista("index", request, response);
        }
    }
}
