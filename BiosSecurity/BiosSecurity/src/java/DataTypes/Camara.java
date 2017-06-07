/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

/**
 *
 * @author Geronimo
 */
public class Camara extends Dispositivo{
    
    private boolean exterior;
    private ServicioVideoVigilancia servicio;
    private Tecnico instalador;
    
    public boolean isExterior() {
        return exterior;
    }

    public void setExterior(boolean exterior) {
        this.exterior = exterior;
    }

    public ServicioVideoVigilancia getServicio() {
        return servicio;
    }

    public void setServicio(ServicioVideoVigilancia servicio) {
        this.servicio = servicio;
    }

    public Tecnico getInstalador() {
        return instalador;
    }

    public void setInstalador(Tecnico instalador) {
        this.instalador = instalador;
    }
    
    public Camara(int numInventario, String descripcionUbicacion, boolean exterior, ServicioVideoVigilancia servicio, Tecnico instalador) {
        super(numInventario, descripcionUbicacion);
        
        setExterior(exterior);
        setServicio(servicio);
        setInstalador(instalador);
        
    }

    
    
}
