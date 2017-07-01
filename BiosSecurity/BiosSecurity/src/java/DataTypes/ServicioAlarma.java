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
public class ServicioAlarma extends Servicio{
    
    private Integer codAnulacion;
    private List<Alarma> alarmas;

    public int getCodAnulacion() {
        return codAnulacion;
    }

    public void setCodAnulacion(Integer codAnulacion) {
        this.codAnulacion = codAnulacion;
    }

    public List<Alarma> getAlarmas() {
        return alarmas;
    }

    public void setAlarmas(List<Alarma> alarmas) {
        this.alarmas = alarmas;
    }
    
    
    public ServicioAlarma() {
        this(0, new Date(), null, null, new ArrayList<Alarma>(), null);
    }
    
    public ServicioAlarma(int numServicio, Date fecha, Boolean monitoreo, Propiedad propiedadCliente, List<Alarma> dispositivos,  Integer codAnulacion) {
        super(numServicio, fecha, monitoreo, propiedadCliente);
        
        setCodAnulacion(codAnulacion);
        setAlarmas(dispositivos);
        
    }

   
}
