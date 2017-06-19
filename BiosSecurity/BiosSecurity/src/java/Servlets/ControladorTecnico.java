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
            
            List<Empleado> empleados = new ArrayList<Empleado>();
            
            if(request.getSession().getAttribute("empleadosTodos") == null){
                
                empleados = FabricaLogica.GetLogicaEmpleado().Listar();
                request.getSession().setAttribute("empleadosTodos", request);
                
            }else{
                
                empleados = (List<Empleado>)request.getSession().getAttribute("empleadosTodos");
            
            }
            
            List<Empleado> empleadosImprimir = new ArrayList<Empleado>();
            
            if(request.getParameter("tipo") == "tecnico"){
                for(Empleado e : empleados){
                    if(e instanceof Tecnico){
                        empleadosImprimir.add(e);
                    }
                }
            }else if(request.getParameter("tipo") == "cobrador"){
                for(Empleado e : empleados){
                    if(e instanceof Cobrador){
                        empleadosImprimir.add(e);
                    }
                }
            }else if(request.getParameter("tipo") == "administrador"){
                for(Empleado e : empleados){
                    if(e instanceof Administrador){
                        empleadosImprimir.add(e);
                    }
                }
            }else{
                cargarMensaje("Seleccione un tipo de empleado para ver la lista", request);
            }
            
            request.setAttribute("empleados", empleadosImprimir);
            cargarMensaje("Cantidad de empleados: " + empleados.size(), request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los empleados.", request);
        }
        
        mostrarVista("index", request, response);
    }
    
    
    
    public void agregar_get(HttpServletRequest request, HttpServletResponse response) {
        
        mostrarVista("agregar", request, response);
        
    }
    
   
}
