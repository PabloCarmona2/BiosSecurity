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
    
    private Boolean exterior;
    private Tecnico instalador;
    
    public Boolean isExterior() {
        return exterior;
    }

    public void setExterior(Boolean exterior) {
        this.exterior = exterior;
    }

    public Tecnico getInstalador() {
        return instalador;
    }

    public void setInstalador(Tecnico instalador) {
        this.instalador = instalador;
    }
    
    public Camara(Integer numInventario, String descripcionUbicacion, Boolean exterior, Tecnico instalador) {
        super(numInventario, descripcionUbicacion);
        
        setExterior(exterior);
        setInstalador(instalador);
        
    }

    
    
}
