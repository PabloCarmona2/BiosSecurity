/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Administrador;
import DataTypes.Cliente;
import DataTypes.Empleado;
import DataTypes.Propiedad;
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
public class ControladorClientes extends Controlador {
    
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
            
            List<Cliente> clientes = new ArrayList<Cliente>();
            
            if(request.getParameter("buscar") != null){
                try {
            
                    Integer.parseInt(request.getParameter("buscar"));

                } catch (NumberFormatException ex) {

                    cargarMensaje("¡ERROR! El numero de documento ingresado no es válido.", request);

                    mostrarVista("index", request, response);

                    return;

                }
            }
            clientes = FabricaLogica.GetLogicaCliente().Listar(request.getParameter("buscar"));
            
            if(clientes.isEmpty()){
                
                clientes = FabricaLogica.GetLogicaCliente().Listar("");
            }
            
            request.setAttribute("clientes", clientes);
            cargarMensaje("Cantidad de clientes: " + clientes.size(), request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los clientes.", request);
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
        int cedula;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("index", request, response);
            
            return;
            
        }
        
        try {
            
            Cliente cliente = (Cliente)FabricaLogica.GetLogicaCliente().Buscar(cedula);
            
            if (cliente != null) {
                
                request.setAttribute("cliente", cliente);
                
                cargarMensaje("¡Cliente encontrado!", request);
                
            } else {
                
                cargarMensaje("¡ERROR! No se encontró ningún cliente con la cédula " + cedula + ".", request);
                
            }
            
            List<Propiedad> propiedades = FabricaLogica.GetLogicaPropiedad().ListarXCliente(cliente);
            
            if (propiedades != null) {
                
                request.setAttribute("propiedades", propiedades);
                
            }
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el administrador.", request);
        }
        
        mostrarVista("ver", request, response);
    }
    
    public void modificarcliente_get(HttpServletRequest request, HttpServletResponse response) {
        try{
            try{
                Administrador admin = (Administrador)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){

                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");

            }
            
            int cedula = 0;
        
            try {

                cedula = Integer.parseInt(request.getParameter("cedula"));

            } catch (NumberFormatException ex) {

                cargarMensaje("¡ERROR! La cédula no es válida.", request);

                mostrarVista("index", request, response);

                return;

            }
            
            Cliente cliente = FabricaLogica.GetLogicaCliente().Buscar(cedula);
            
            request.setAttribute("cliente", cliente);
            
        }catch(Exception ex){
            cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
        }
        
        cargarMensaje("Cliente encontrado!.", request);
        mostrarVista("modificarCliente", request, response);
        
    }
    
    public void modificarcliente_post(HttpServletRequest request, HttpServletResponse response) {
        
        int cedula = 0;
        
        try {
            
            cedula = Integer.parseInt(request.getParameter("cedula"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! La cédula no es válida.", request);
            
            mostrarVista("agregarAdministrador", request, response);
            
            return;
            
        }
        
        String nombre = request.getParameter("nombre");
        
        String barrio = request.getParameter("barrio");
        
        String dirCobro = request.getParameter("dirCobro");
        
        String telefono = request.getParameter("telefono");
        
        
        
        Cliente cliente = new Cliente(cedula, nombre, barrio, dirCobro, telefono);
        
        try {
            
            FabricaLogica.GetLogicaCliente().Modificar(cliente);
            
            cargarMensaje("¡Cliente modificado con éxito!", request.getSession());
            
            response.sendRedirect("clientes");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al agregar el cliente.", request);
            
            mostrarVista("modificarCliente", request, response);
            
        }
    }
    
    
    public void modificarpropiedad_get(HttpServletRequest request, HttpServletResponse response) {
        try{
            try{
                Administrador admin = (Administrador)request.getSession().getAttribute("empleadoLogueado");
            }catch(Exception ex){

                request.getSession().setAttribute("mensajeLogueo", "El usuario logueado no tiene los permisos para ingresar a este sitio!");

                response.sendRedirect("login");

            }
            
            int cedula = 0;
        
            try {

                cedula = Integer.parseInt(request.getParameter("cedulaCliente"));

            } catch (NumberFormatException ex) {

                cargarMensaje("¡ERROR! La cédula no es válida.", request);

                mostrarVista("ver", request, response);

                return;

            }
            
            int idProp = 0;
        
            try {

                idProp = Integer.parseInt(request.getParameter("idProp"));

            } catch (NumberFormatException ex) {

                cargarMensaje("¡ERROR! El id de propiedad no es válido.", request);

                mostrarVista("ver", request, response);

                return;

            }
            
            Cliente cliente = FabricaLogica.GetLogicaCliente().Buscar(cedula);
            Propiedad propiedad = FabricaLogica.GetLogicaPropiedad().Buscar(idProp, cedula);
            
            request.getSession().setAttribute("cliente", cliente);
            request.setAttribute("propiedad", propiedad);
            
        }catch(Exception ex){
            cargarMensaje("¡ERROR! al intentar realizar esta accion!.", request);
        }
        
        cargarMensaje("Propiedad encontrada!.", request);
        mostrarVista("modificarPropiedad", request, response);
        
    }
    
    public void modificarpropiedad_post(HttpServletRequest request, HttpServletResponse response) {
        
        int idProp = 0;
        
        try {
            
            idProp = Integer.parseInt(request.getParameter("numPropiedad"));
            
        } catch (NumberFormatException ex) {
            
            cargarMensaje("¡ERROR! El numero de propiedad no es válido.", request);
            
            mostrarVista("modificarPropiedad", request, response);
            
            return;
            
        }
        
        String tipo = request.getParameter("tipoPropiedad");
        
        String direccion = request.getParameter("direccion");
        
        Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
        request.getSession().removeAttribute("cliente");
        
        
        
        Propiedad propiedad = new Propiedad(idProp, tipo, direccion, cliente);
        
        try {
            
            FabricaLogica.GetLogicaPropiedad().Modificar(propiedad);
            
            cargarMensaje("¡Propiedad modificada con éxito!", request.getSession());
            
            response.sendRedirect("clientes");
            
        } catch (Exception ex) {
            
            cargarMensaje("¡ERROR! Se produjo un error al modificar la propiedad.", request);
            
            mostrarVista("modificarPropiedad", request, response);
            
        }
    }
    
    
    
}
