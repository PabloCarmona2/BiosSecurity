/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public abstract class Servicio {
    
    private int numServicio;
    private Date fecha;
    private Boolean monitoreo;
    private Propiedad propiedadCliente;

    public int getNumServicio() {
        return numServicio;
    }

    public void setNumServicio(int numServicio) {
        this.numServicio = numServicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isMonitoreo() {
        return monitoreo;
    }

    public void setMonitoreo(boolean monitoreo) {
        this.monitoreo = monitoreo;
    }

    public Propiedad getPropiedadCliente() {
        return propiedadCliente;
    }

    public void setPropiedadCliente(Propiedad propiedadCliente) {
        this.propiedadCliente = propiedadCliente;
    }
    
    public Servicio(){
        
        this(0,new Date(), null, null);
    }
    
    public Servicio(int numServicio, Date fecha, Boolean monitoreo, Propiedad propiedadCliente){
        
        setNumServicio(numServicio);
        setFecha(fecha);
        setMonitoreo(monitoreo);
        setPropiedadCliente(propiedadCliente);
    }
    
}
