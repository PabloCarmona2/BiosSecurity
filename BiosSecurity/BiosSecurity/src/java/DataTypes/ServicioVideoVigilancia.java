/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class ServicioVideoVigilancia extends Servicio{
    
    private Boolean terminal;
    private List<Camara> camaras;

    public List<Camara> getCamaras() {
        return camaras;
    }

    public void setCamaras(List<Camara> camaras) {
        this.camaras = camaras;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(Boolean terminal) {
        this.terminal = terminal;
    }
    
    
    public ServicioVideoVigilancia() {
        this(0,new Date(), null, null, new ArrayList<Camara>(), null);
    }
    
    public ServicioVideoVigilancia(int numServicio, Date fecha, Boolean monitoreo, Propiedad propiedadCliente, List<Camara> dispositivos, Boolean terminal) {
        super(numServicio, fecha, monitoreo, propiedadCliente);
        
        setTerminal(terminal);
        setCamaras(dispositivos);
    }
    
}
