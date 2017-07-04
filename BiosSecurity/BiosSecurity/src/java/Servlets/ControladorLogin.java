/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Empleado;
import Logica.FabricaLogica;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ControladorLogin extends Controlador{
    
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        
        if(request.getSession().getAttribute("mensajeLogueo") != null){
            
            cargarMensaje((String)request.getSession().getAttribute("mensajeLogueo"), request.getSession());
            
            if(request.getSession().getAttribute("empleadoLogueado") != null){
                mostrarVista("MenuPrincipal", request, response);
                request.getSession().removeAttribute("mensajeLogueo");
                return;
            }else{
                mostrarVista("login", request, response);
                request.getSession().removeAttribute("mensajeLogueo");
                return;
            }
        }
        
        if(request.getSession().getAttribute("mensaje") != null){
            
            mostrarVista("login", request, response);
            
            request.getSession().removeAttribute("mensaje");
        }
        
        if(request.getSession().getAttribute("empleadoLogueado") != null){
            mostrarVista("MenuPrincipal", request, response);
        }else{
            mostrarVista("login", request, response);
        }
        
    }   
    public void login_post(HttpServletRequest request, HttpServletResponse response){
        
        int cedula;
        String clave;
        
        try {
            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no tiene formato válido.", request);
            
            mostrarVista("login", request, response);
            
            return;
        }
        
        try {
            clave = request.getParameter("clave");
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La clave no tiene formato válido.", request);
            
            mostrarVista("login", request, response);
            
            return;
        }
        
        try{
            //habria que preguntar que tipo de empleado es de acuerdo esto le mostramos la vista correspondiente
            Empleado empleado = FabricaLogica.GetLogicaEmpleado().LoginEmpleado(cedula, clave);
            if (empleado != null) {
                request.getSession().setAttribute("empleadoLogueado", empleado);
                
                cargarMensaje("¡Login exitoso!", request);
                mostrarVista("MenuPrincipal", request, response);
            } else {
                cargarMensaje("¡ERROR! usuario o contraseña erroneos ", request);
            }
        }catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al intentar login.", request);
        }
        mostrarVista("login", request, response);
    }
    public void logout_get(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        if(request.getSession().getAttribute("empleadoLogueado")!= null){
            
            request.getSession().removeAttribute("empleadoLogueado");
            cargarMensaje("Empleado deslogueado con exito!!.", request.getSession());
            
            response.sendRedirect("login");
        }
    }
}
