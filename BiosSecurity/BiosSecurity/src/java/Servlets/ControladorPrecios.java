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
 * @author Geronimo
 */
public class ControladorPrecios extends Controlador {
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        
        verificarLogueo(request, response);
        
        mostrarVista("index", request, response);
        
    }
    
    public void setear_post(HttpServletRequest request, HttpServletResponse response) {
        
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
        
        Date fIngreso = new Date();
        
        
        Administrador admin = new Administrador(cedula, nombre, clave, fIngreso, sueldo);
        
        try {
            
            FabricaLogica.GetLogicaEmpleado().Agregar(admin);
            
            cargarMensaje("¡Administrador agregado con éxito!", request.getSession());
            
            List<Empleado> empleados = FabricaLogica.GetLogicaEmpleado().Listar("admin");
                
            request.getSession().removeAttribute("empleadosTodos");
            request.getSession().setAttribute("empleadosTodos", empleados);
            
            response.sendRedirect("administrador");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el administrador.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
        }
    }
}
