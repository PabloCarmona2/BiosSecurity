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
    
    private Tecnico instalador;

    public Tecnico getInstalador() {
        return instalador;
    }

    public void setInstalador(Tecnico instalador) {
        this.instalador = instalador;
    }
    public Alarma(){
        this(0,null,null);
    }
    public Alarma(Integer numInventario, String descripcionUbicacion, Tecnico instalador) {
        super(numInventario, descripcionUbicacion);
        
        setInstalador(instalador);
        
    }
    
}
