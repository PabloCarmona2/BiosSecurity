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
public class Propiedad {
    
    private int idProp;
    private String tipo;
    private String direccion;
    private Cliente dueño;

    public int getIdProp() {
        return idProp;
    }

    public void setIdProp(int idProp) {
        this.idProp = idProp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente getDueño() {
        return dueño;
    }

    public void setDueño(Cliente dueño) {
        this.dueño = dueño;
    }
    
    public Propiedad(int idProp, String tipo, String direccion, Cliente dueño){
        
        setIdProp(idProp);
        setTipo(tipo);
        setDireccion(direccion);
        setDueño(dueño);
        
    }
    
}
