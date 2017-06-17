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
public class ServicioAlarma extends Servicio{
    
    private int codAnulacion;
    private List<Alarma> alarmas;

    public int getCodAnulacion() {
        return codAnulacion;
    }

    public void setCodAnulacion(int codAnulacion) {
        this.codAnulacion = codAnulacion;
    }

    public List<Alarma> getAlarmas() {
        return alarmas;
    }

    public void setAlarmas(List<Alarma> alarmas) {
        this.alarmas = alarmas;
    }
    
    
    
    
    public ServicioAlarma(int numServicio, Date fecha, boolean monitoreo, Propiedad propiedadCliente, List<Alarma> dispositivos,  int codAnulacion) {
        super(numServicio, fecha, monitoreo, propiedadCliente);
        
        setCodAnulacion(codAnulacion);
        setAlarmas(dispositivos);
        
    }

   
}
