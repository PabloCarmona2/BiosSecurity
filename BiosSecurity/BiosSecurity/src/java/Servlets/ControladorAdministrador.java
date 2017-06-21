/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Empleado;
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
 * @author matias
 */
public class ControladorAdministrador extends Controlador {

   public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            List<Empleado> empleados = new ArrayList<Empleado>();
            
            if(request.getSession().getAttribute("empleadosTodos") == null){
                
                empleados = FabricaLogica.GetLogicaEmpleado().Listar();
                request.getSession().setAttribute("empleadosTodos", request);
                
            }else{
                
                empleados = (List<Empleado>)request.getSession().getAttribute("empleadosTodos");
            
            }
            
            List<Administrador> empleadosImprimir = new ArrayList<Administrador>();
            
            if(request.getParameter("tipo") == "tecnico"){
                for(Empleado admin : empleados){
                    if(admin instanceof Administrador){
                        empleadosImprimir.add((Administrador)admin);
                    }
                }
            }else{
                
                cargarMensaje("No ha seleccionado este tipo de empleado", request);
                
                mostrarVista("index", request, response);
            
                return;
            }
            
            request.setAttribute("empleados", empleadosImprimir);
            cargarMensaje("Cantidad de empleados: " + empleados.size(), request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los administradores.", request);
        }
        
        mostrarVista("index", request, response);
    }
    public void agregar_get(HttpServletRequest request, HttpServletResponse response) {
        
        mostrarVista("agregar", request, response);
        
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response) {
        
        int cedula = 0;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("agregar", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("agregar", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        Date fIngreso = new Date();
        
        
        Administrador admin = new Administrador(cedula, nombre, clave, fIngreso, sueldo);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Agregar(admin);
            
            cargarMensaje("¡Administrador agregado con éxito!", request.getSession());
            
            response.sendRedirect("administrador");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el administrador.", request);
            
            mostrarVista("agregar", request, response);
            
        }
    }
    public void ver_get(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("ver", request, response);
            
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
        
        mostrarVista("ver", request, response);
    }
    public void modificar_get(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("modificar", request, response);
            
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
        
        mostrarVista("modificar", request, response);
    }
    
    public void modificar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("modificar", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        double sueldo = 0;
        
        try {
            
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El sueldo no es válido.", request);
            
            mostrarVista("modificar", request, response);
            
            return;
            
            
        }
        
        String clave = request.getParameter("clave");
        
        Date fIngreso = new Date();
        
        
        Administrador admin = new Administrador(cedula, nombre, clave, fIngreso, sueldo);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Modificar(admin);
            
            cargarMensaje("¡Tecnico modificado con éxito!", request.getSession());
            
            response.sendRedirect("empleados");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al modificar el empleado.", request);
            
            mostrarVista("modificar", request, response);
            
        }
    }
    public void eliminar_get(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("eliminar", request, response);
            
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
        
        mostrarVista("eliminar", request, response);
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) {
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("eliminar", request, response);
            
            return;
            
        }
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Eliminar((Administrador)request.getAttribute("empleado"));
            
            cargarMensaje("¡Empleado eliminado con éxito!", request.getSession());
            
            response.sendRedirect("administrador");
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el empleado.", request);
            
            mostrarVista("eliminar", request, response);
        }
    }
}
