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
            
            List<Tecnico> tecnicos = new ArrayList<Tecnico>();
            
            if(request.getSession().getAttribute("empleadosTodos") == null){
                
                tecnicos = FabricaLogica.GetLogicaEmpleado().ListarTecnico();
                request.getSession().setAttribute("empleadosTodos", request);
                
            }else{
                
                tecnicos = (List<Tecnico>)request.getSession().getAttribute("empleadosTodos");
            
            }
            
            List<Tecnico> empleadosImprimir = new ArrayList<Tecnico>();
            
            if(request.getParameter("tipo") == "tecnico"){
                for(Empleado t : tecnicos){
                    
                        empleadosImprimir.add((Tecnico)t);
                    
                }
            }else{
                
                cargarMensaje("No ha seleccionado este tipo de empleado", request);
                
                mostrarVista("index", request, response);
            
                return;
            }
            
            request.setAttribute("empleados", empleadosImprimir);
            cargarMensaje("Cantidad de empleados: " + tecnicos.size(), request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los tecnicos.", request);
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
        
        Date fIngreso = new Date();
        
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
                
                request.setAttribute("empleado", tecnico);
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
        
        Date fIngreso = new Date();
        
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
                
                request.setAttribute("empleado", tecnico);
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
            
            FabricaLogica.GetLogicaEmpleado().Eliminar((Tecnico)request.getAttribute("empleado"));
            
            cargarMensaje("¡Empleado eliminado con éxito!", request.getSession());
            
            response.sendRedirect("tecnicos");
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el empleado.", request);
            
            mostrarVista("eliminarTecnico", request, response);
        }
    }
    
    
}
