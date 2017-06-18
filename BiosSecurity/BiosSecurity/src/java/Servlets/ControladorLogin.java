/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Empleado;
import Logica.FabricaLogica;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ControladorLogin extends Controlador{
    
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("Login", request, response);
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response){
        
        int cedula;
        String clave;
        
        try {
            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no tiene formato válido.", request);
            
            mostrarVista("Login", request, response);
            
            return;
        }
        
        try {
            clave = request.getParameter("clave");
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La clave no tiene formato válido.", request);
            
            mostrarVista("Login", request, response);
            
            return;
        }
        
        try{
            
            Empleado empleado = FabricaLogica.GetLogicaEmpleado().LoginEmpleado(cedula, clave);
            if (empleado != null) {
                request.setAttribute("empleado", empleado);
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
    
}
