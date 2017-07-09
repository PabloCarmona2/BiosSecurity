/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Empleado;
import DataTypes.Tecnico;
import Logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matias
 */
public class ControladorAdministrador extends Controlador {

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

                    mostrarVista("listaAdministradores", request, response);

                    return;

                }
            }
            empleados = FabricaLogica.GetLogicaEmpleado().Listar("admin", request.getParameter("buscar"));
            
            if(empleados.isEmpty() || !(empleados.toArray()[0] instanceof Administrador)){
                
                cargarMensaje("No se encontro ningun empleado con la cedula" + (request.getParameter("buscar")) + ".", request);
                empleados = FabricaLogica.GetLogicaEmpleado().Listar("admin", "");
                request.getSession().setAttribute("empleadosTodos", empleados);
                
            }else{
                
                cargarMensaje("Cantidad de empleados: " + empleados.size(), request);
                request.getSession().setAttribute("empleadosTodos", empleados);
            
            }
            
            request.setAttribute("empleados", empleados);
            
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los administradores.", request);
        }
        
        mostrarVista("listaAdministradores", request, response);
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
        mostrarVista("agregarAdministrador", request, response);
        
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response) {
        
        int cedula = 0;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fIngreso = new Date();
        
        String fechaIngresada = request.getParameter("fIngreso");
        String charEvaluar = "-";
        
        if(fechaIngresada.length() != 10){
               
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, (ej: mes 1 = 01, dia 5 = 05).", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
            return;
            
        }
        
        if(fechaIngresada.charAt(4) != charEvaluar.charAt(0)){
            
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, comience con el año.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
            return;
            
        }

        try {

            fIngreso = formatoDelTexto.parse(fechaIngresada);

        } catch (Exception ex) {

            cargarMensaje("¡ERROR! La fecha ingresada no tiene el formato correcto.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
            return;
        }
        
        
        Administrador admin = new Administrador(cedula, nombre, clave, fIngreso, sueldo);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Agregar(admin);
            
            cargarMensaje("¡Administrador agregado con éxito!", request.getSession());
            
            response.sendRedirect("administrador");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el administrador." + ex.getMessage(), request);
            
            mostrarVista("agregarAdministrador", request, response);
            
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
            
            mostrarVista("verAdministrador", request, response);
            
            return;
            
        }
        
        try {
            
            Administrador admin = (Administrador)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (admin != null) {
                
                request.setAttribute("empleado", admin);
                
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún administrador con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el administrador.", request);
        }
        
        mostrarVista("verAdministrador", request, response);
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
            
            mostrarVista("modificarAdministrador", request, response);
            
            return;
            
        }
        
        try {
            
            Administrador admin = (Administrador)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (admin != null) {
                
                request.setAttribute("empleado", admin);
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                request.setAttribute("ocultarFormulario", true);
                
                cargarMensaje("¡ERROR! No se encontró ningún administrador con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al buscar el administrador.", request);
            
        }
        
        mostrarVista("modificarAdministrador", request, response);
    }
    
    public void modificar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("modificarAdministrador", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("modificarAdministrador", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fIngreso = new Date();
        
        String fechaIngresada = request.getParameter("fIngreso");
        String charEvaluar = "-";
        
        if(fechaIngresada.length() != 10){
               
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, (ej: mes 1 = 01, dia 5 = 05).", request);
            
            mostrarVista("modificarAdministrador", request, response);
            
            return;
            
        }
        if(fechaIngresada.charAt(4) != charEvaluar.charAt(0)){
            
            cargarMensaje("¡ERROR! Debe respetar el formato indicado, comience con el año.", request);
            
            mostrarVista("modificarAdministrador", request, response);
            
            return;
            
        }

        try {

            fIngreso = formatoDelTexto.parse(fechaIngresada);

        } catch (Exception ex) {

            cargarMensaje("¡ERROR! La fecha ingresada no tiene el formato correcto.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
            return;
        }
        
        
        Administrador admin = new Administrador(cedula, nombre, clave, fIngreso, sueldo);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Modificar(admin);
            
            cargarMensaje("¡Administrador modificado con éxito!", request.getSession());
            
            response.sendRedirect("administrador");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al modificar el empleado." + ex.getMessage(), request);
            
            mostrarVista("modificarAdministrador", request, response);
            
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
            
            mostrarVista("eliminarAdministrador", request, response);
            
            return;
            
        }
        
        try {
            
            Administrador admin = (Administrador)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (admin != null) {
                
                request.setAttribute("empleado", admin);
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún administrador con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al buscar el administrador.", request);
            
        }
        
        mostrarVista("eliminarAdministrador", request, response);
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("eliminarAdministrador", request, response);
            
            return;
            
        }
        
        Administrador adminLogueado = (Administrador)request.getSession().getAttribute("empleadoLogueado");
        
        if(cedula == adminLogueado.getCedula()){
            
            cargarMensaje("¡ERROR! No puede autoeliminarse del sistema.", request);
            
            mostrarVista("listaAdministradores", request, response);
            
            return;
        }
        
        try {
            Empleado emp = FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            FabricaLogica.GetLogicaEmpleado().Eliminar(emp);
            
            cargarMensaje("¡Empleado eliminado con éxito!", request.getSession());
            
            response.sendRedirect("administrador");
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el empleado." + ex.getMessage(), request);
            
            mostrarVista("eliminarAdministrador", request, response);
        }
    }
}
