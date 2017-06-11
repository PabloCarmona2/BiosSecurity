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
    private boolean monitoreo;
    private Propiedad propiedadCliente;
    private List<Dispositivo> dispositivos;

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

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
    
    public Servicio(int numServicio, Date fecha, boolean monitoreo, Propiedad propiedadCliente, List<Dispositivo> dispositivos){
        
        setNumServicio(numServicio);
        setFecha(fecha);
        setMonitoreo(monitoreo);
        setPropiedadCliente(propiedadCliente);
        setDispositivos(dispositivos);
    }
    
}
