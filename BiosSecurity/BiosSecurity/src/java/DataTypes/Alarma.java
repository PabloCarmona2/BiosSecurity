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
public class Alarma extends Dispositivo{
    
    private ServicioAlarma servicio;
    private Tecnico instalador;

    public ServicioAlarma getServicio() {
        return servicio;
    }

    public void setServicio(ServicioAlarma servicio) {
        this.servicio = servicio;
    }

    public Tecnico getInstalador() {
        return instalador;
    }

    public void setInstalador(Tecnico instalador) {
        this.instalador = instalador;
    }
    
    public Alarma(int numInventario, String descripcionUbicacion, ServicioAlarma servicio, Tecnico instalador) {
        super(numInventario, descripcionUbicacion);
        
        setServicio(servicio);
        setInstalador(instalador);
        
    }
    
}
