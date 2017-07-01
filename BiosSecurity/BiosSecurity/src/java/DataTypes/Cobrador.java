/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import java.util.Date;

/**
 *
 * @author Geronimo
 */
public class Cobrador extends Empleado{
    
    private  String transporte;
    
    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public Cobrador() {
        this(0,null,null,new Date(),0,null);
        
    }
    
    public Cobrador(int cedula, String nombre, String clave, Date fIngreso, double sueldo, String transporte) {
        super(cedula, nombre, clave, fIngreso, sueldo);
        
        setTransporte(transporte);
    }
    
}
