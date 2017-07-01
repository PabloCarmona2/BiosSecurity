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
public class Administrador extends Empleado{
    public Administrador() {
        this(0,null,null,new Date(),1);
    }
    public Administrador(int cedula, String nombre, String clave, Date fIngreso, double sueldo) {
        super(cedula, nombre, clave, fIngreso, sueldo);
    }
    
}
