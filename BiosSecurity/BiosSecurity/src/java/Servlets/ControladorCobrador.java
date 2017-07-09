/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Cobrador;
import DataTypes.Empleado;
import DataTypes.Tecnico;
import Logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ControladorCobrador extends Controlador {

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
            
            List<Empleado> empleados = new ArrayList<Empleado>();
            
            if(request.getParameter("buscar") != null && request.getParameter("buscar") != ""){
                try {
            
                    Integer.parseInt(request.getParameter("buscar"));

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! El numero de documento ingresado no es válido.", request);

                    mostrarVista("listaCobradores", request, response);

                    return;

                }
            }
            
            empleados = FabricaLogica.GetLogicaEmpleado().Listar("cobrador", request.getParameter("buscar"));
            
            if(empleados.isEmpty() || !(empleados.toArray()[0] instanceof Cobrador)){
                
                cargarMensaje("No se encontro ningun empleado con la cedula" + (request.getParameter("buscar")) + ".", request);
                empleados = FabricaLogica.GetLogicaEmpleado().Listar("cobrador", "");
                request.getSession().setAttribute("empleadosTodosT", empleados);
                
            }else{
                cargarMensaje("Cantidad de empleados: " + empleados.size(), request);
                request.getSession().setAttribute("empleadosTodosT", empleados);
            
            }
            
            
            request.setAttribute("empleados", empleados);
            
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los cobradores.", request);
        }
        
        mostrarVista("listaCobradores", request, response);
    }
    
    
    
    public void agregar_get(HttpServletRequest request, HttpServletResponse response) {
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
        mostrarVista("agregarCobrador", request, response);
        
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response) {
        
        int cedula = 0;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
            if(cedula == 0)
            {
                cargarMensaje("¡ERROR! La cédula no puede ser 0.", request);
            
                mostrarVista("agregarCobrador", request, response);
            }
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        
            
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fIngreso = new Date();
        
        String fechaIngresada = request.getParameter("fIngreso");
        String charEvaluar = "-";
        
        if(fechaIngresada.length() != 10){
               
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, (ej: mes 1 = 01, dia 5 = 05).", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
            
        }
        if(fechaIngresada.charAt(4) != charEvaluar.charAt(0)){
            
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, comience con el año.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
            
        }

        try {

            fIngreso = formatoDelTexto.parse(fechaIngresada);

        } catch (Exception ex) {

            cargarMensaje("¡ERROR! La fecha ingresada no tiene el formato correcto.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
        }
        
        String transporte = request.getParameter("transporte");
        
        Cobrador cobrador = new Cobrador(cedula, nombre, clave, fIngreso, sueldo, transporte);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Agregar(cobrador);
            
            cargarMensaje("¡Cobrador agregado con éxito!", request.getSession());
            
            response.sendRedirect("cobradores");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el cobrador.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
        }
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
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("verCobrador", request, response);
            
            return;
            
        }
        
        try {
            
            Cobrador cobrador = (Cobrador)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (cobrador != null) {
                
                request.setAttribute("empleado", cobrador);
                
                cargarMensaje("¡Cobrador encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún cobrador con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el cobrador.", request);
        }
        
        mostrarVista("verCobrador", request, response);
    }
    
    public void modificar_get(HttpServletRequest request, HttpServletResponse response) {
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
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("modificarCobrador", request, response);
            
            return;
            
        }
        
        try {
            
            Cobrador cobrador = (Cobrador)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (cobrador != null) {
                
                request.getSession().setAttribute("empleado", cobrador);
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                request.setAttribute("ocultarFormulario", true);
                
                cargarMensaje("¡ERROR! No se encontró ningún cobrador con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al buscar el cobrador.", request);
            
        }
        
        mostrarVista("modificarCobrador", request, response);
    }
    
    public void modificar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("modificarCobrador", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("modificarCobrador", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fIngreso = new Date();
        
        String fechaIngresada = request.getParameter("fIngreso");
        String charEvaluar = "-";
        
        if(fechaIngresada.length() != 10){
               
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, (ej: mes 1 = 01, dia 5 = 05).", request);
            
            mostrarVista("modificarCobrador", request, response);
            
            return;
            
        }
        if(fechaIngresada.charAt(4) != charEvaluar.charAt(0)){
            
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, comience con el año.", request);
            
            mostrarVista("modificarCobrador", request, response);
            
            return;
            
        }

        try {

            fIngreso = formatoDelTexto.parse(fechaIngresada);

        } catch (Exception ex) {

            cargarMensaje("¡ERROR! La fecha ingresada no tiene el formato correcto.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
        }
        
        String Transporte = request.getParameter("transporte");
        
        Cobrador cobrador = new Cobrador(cedula, nombre, clave, fIngreso, sueldo, Transporte);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Modificar(cobrador);
            
            cargarMensaje("¡Cobrador modificado con éxito!", request.getSession());
            
            request.removeAttribute("empleado");
            
            response.sendRedirect("cobradores");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al modificar el empleado.", request);
            
            mostrarVista("modificarCobrador", request, response);
            
        }
    }
    
    public void eliminar_get(HttpServletRequest request, HttpServletResponse response) {
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
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("eliminarCobrador", request, response);
            
            return;
            
        }
        
        try {
            
            Cobrador cobrador = (Cobrador)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (cobrador != null) {
                
                request.getSession().setAttribute("empleado", cobrador);
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún cobrador con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al buscar el cobrador.", request);
            
        }
        
        mostrarVista("eliminarCobrador", request, response);
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("eliminarCobrador", request, response);
            
            return;
            
        }
        
        try {
            
            Cobrador cobrador = (Cobrador)request.getSession().getAttribute("empleado");
            
            FabricaLogica.GetLogicaEmpleado().Eliminar(cobrador);
            
            cargarMensaje("¡Empleado eliminado con éxito!", request.getSession());
            
            request.removeAttribute("empleado");
            
            response.sendRedirect("cobradores");
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el empleado.", request);
            
            mostrarVista("eliminarCobrador", request, response);
        }
    }
   
    
}
