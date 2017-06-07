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
public class Cliente {
    
    private int cedula;
    private String nombre;
    private String barrio;
    private String dirCobro;
    private String telefono;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDirCobro() {
        return dirCobro;
    }

    public void setDirCobro(String dirCobro) {
        this.dirCobro = dirCobro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Cliente(int cedula, String nombre, String barrio, String dirCobro, String telefono){
        
        setCedula(cedula);
        setNombre(nombre);
        setBarrio(barrio);
        setDirCobro(dirCobro);
        setTelefono(telefono);
        
    }
    
}
