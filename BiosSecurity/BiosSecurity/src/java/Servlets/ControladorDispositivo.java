/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataTypes.Dispositivo;
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
public class ControladorDispositivo extends Controlador {
    @Override
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
            
            if(request.getSession().getAttribute("dispositivos") == null){
                
                dispositivos = FabricaLogica.GetLogicaDispositivo().Listar();
                request.getSession().setAttribute("dispositivos", request);
                
            }else{
                
                dispositivos = (List<Dispositivo>)request.getSession().getAttribute("dispositivos");
            
            }
            
            cargarMensaje("Cantidad de dispositivos: " + dispositivos.size(), request);
            
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los dispositivos.", request);
        }
        
        mostrarVista("index", request, response);
    }
    
    
}
