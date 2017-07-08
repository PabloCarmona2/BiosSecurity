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
    
    private Boolean eCamaras;
    private Boolean eAlarmas;

    public Boolean geteCamaras() {
        return eCamaras;
    }

    public void seteCamaras(Boolean eCamaras) {
        this.eCamaras = eCamaras;
    }

    public Boolean geteAlarmas() {
        return eAlarmas;
    }

    public void seteAlarmas(Boolean eAlarmas) {
        this.eAlarmas = eAlarmas;
    }

    
    public Tecnico() {
        this(0,null,null,new Date(),0,false, false);
    }
    
    public Tecnico(int cedula, String nombre, String clave, Date fIngreso, double sueldo, Boolean eCamaras, Boolean eAlarmas) {
        super(cedula, nombre, clave, fIngreso, sueldo);
        
        this.seteAlarmas(eAlarmas);
        this.seteCamaras(eCamaras);
    }
}
