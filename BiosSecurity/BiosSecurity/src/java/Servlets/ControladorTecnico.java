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
public class ControladorTecnico extends Controlador {
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            verificarLogueo(request, response);
            
            
            try{
                Administrador admin = (Administrador)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){
                
                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");
                
            }
            
            List<Empleado> empleados = new ArrayList<Empleado>();
            
            if(request.getParameter("buscar") != null && request.getParameter("buscar") != ""){
                try {
            
                    Integer.parseInt(request.getParameter("buscar"));

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! El numero de documento ingresado no es válido.", request);

                    mostrarVista("listaTecnicos", request, response);

                    return;

                }
            }
            
            empleados = FabricaLogica.GetLogicaEmpleado().Listar("tecnico", request.getParameter("buscar"));
            
            if(empleados.isEmpty() || !(empleados.toArray()[0] instanceof Tecnico)){
                
                empleados = FabricaLogica.GetLogicaEmpleado().Listar("tecnico", "");
                request.getSession().setAttribute("empleadosTodosT", empleados);
                
            }else{
                
                request.getSession().setAttribute("empleadosTodosT", empleados);
            
            }
            
            
            request.setAttribute("empleados", empleados);
            cargarMensaje("Cantidad de empleados: " + empleados.size(), request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los tecnicos.", request);
        }
        
        mostrarVista("listaTecnicos", request, response);
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
        
        mostrarVista("agregarTecnico", request, response);
        
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response) {
        
        int cedula = 0;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
            if(cedula == 0)
            {
                cargarMensaje("¡ERROR! La cédula no puede ser 0.", request);
            
                mostrarVista("agregarTecnico", request, response);
            }
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("agregarTecnico", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("agregarTecnico", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy/MM/dd");

        Date fIngreso = new Date();
        
        String fechaIngresada = request.getParameter("fIngreso");

        try {

            fIngreso = formatoDelTexto.parse(fechaIngresada);

        } catch (Exception ex) {

            cargarMensaje("¡ERROR! La fecha ingresada no tiene el formato correcto.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
        }
        
        String especializacion = request.getParameter("especializacion");
        
        Tecnico tecnico = new Tecnico(cedula, nombre, clave, fIngreso, sueldo, especializacion);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Agregar(tecnico);
            
            cargarMensaje("¡Tecnico agregado con éxito!", request.getSession());
            
            response.sendRedirect("tecnicos");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el tecnico.", request);
            
            mostrarVista("agregarTecnico", request, response);
            
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
            
            mostrarVista("verTecnico", request, response);
            
            return;
            
        }
        
        try {
            
            Tecnico tecnico = (Tecnico)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (tecnico != null) {
                
                request.setAttribute("empleado", tecnico);
                
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún tecnico con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el tecnico.", request);
        }
        
        mostrarVista("verTecnico", request, response);
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
            
            mostrarVista("modificarTecnico", request, response);
            
            return;
            
        }
        
        try {
            
            Tecnico tecnico = (Tecnico)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (tecnico != null) {
                
                request.getSession().setAttribute("empleado", tecnico);
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                request.setAttribute("ocultarFormulario", true);
                
                cargarMensaje("¡ERROR! No se encontró ningún tecnico con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al buscar el tecnico.", request);
            
        }
        
        mostrarVista("modificarTecnico", request, response);
    }
    
    public void modificar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("modificarTecnico", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("modificarTecnico", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy/MM/dd");

        Date fIngreso = new Date();
        
        String fechaIngresada = request.getParameter("fIngreso");

        try {

            fIngreso = formatoDelTexto.parse(fechaIngresada);

        } catch (Exception ex) {

            cargarMensaje("¡ERROR! La fecha ingresada no tiene el formato correcto.", request);
            
            mostrarVista("agregarCobrador", request, response);
            
            return;
        }
        
        String especializacion = request.getParameter("especializacion");
        
        Tecnico tecnico = new Tecnico(cedula, nombre, clave, fIngreso, sueldo, especializacion);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Modificar(tecnico);
            
            cargarMensaje("¡Tecnico modificado con éxito!", request.getSession());
            
            
            response.sendRedirect("tecnicos");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al modificar el empleado.", request);
            
            mostrarVista("modificarTecnico", request, response);
            
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
            
            mostrarVista("eliminarTecnico", request, response);
            
            return;
            
        }
        
        try {
            
            Tecnico tecnico = (Tecnico)FabricaLogica.GetLogicaEmpleado().Buscar(cedula);
            
            if (tecnico != null) {
                
                request.getSession().setAttribute("empleado", tecnico);
                cargarMensaje("¡Empleado encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún tecnico con la cédula " + cedula + ".", request);
                
            }
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al buscar el tecnico.", request);
            
        }
        
        mostrarVista("eliminarTecnico", request, response);
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("eliminarTecnico", request, response);
            
            return;
            
        }
        
        try {
            
            Tecnico tecnico = (Tecnico)request.getSession().getAttribute("empleado");
            
            FabricaLogica.GetLogicaEmpleado().Eliminar(tecnico);
            
            cargarMensaje("¡Empleado eliminado con éxito!", request.getSession());
            
            
            response.sendRedirect("tecnicos");
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el empleado.", request);
            
            mostrarVista("eliminarTecnico", request, response);
        }
    }
    
    
}
