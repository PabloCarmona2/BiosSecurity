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
public class Tecnico extends Empleado{
    
    private String especializacion;

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }
    
    public Tecnico(int cedula, String nombre, String clave, Date fIngreso, double sueldo, String especializacion) {
        super(cedula, nombre, clave, fIngreso, sueldo);
        
        setEspecializacion(especializacion);
    }
}
