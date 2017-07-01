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
public abstract class Dispositivo {
    
    private Integer numInventario;
    private String descripcionUbicacion;

    public int getNumInventario() {
        return numInventario;
    }

    public void setNumInventario(int numInventario) {
        this.numInventario = numInventario;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }
    
    public Dispositivo(){
        
        this(0,null);
    }
    
    public Dispositivo(Integer numInventario, String descripcionUbicacion){
        
        setNumInventario(numInventario);
        setDescripcionUbicacion(descripcionUbicacion);
        
    }
    
}
