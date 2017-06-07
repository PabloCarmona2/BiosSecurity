/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import java.util.Date;

/**
 *
 * @author Geronimo
 */
public class ServicioVideoVigilancia extends Servicio{
    
    private boolean terminal;

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }
    
    public ServicioVideoVigilancia(int numServicio, Date fecha, boolean monitoreo, Propiedad propiedadCliente, boolean terminal) {
        super(numServicio, fecha, monitoreo, propiedadCliente);
        
        setTerminal(terminal);
    }
    
}
