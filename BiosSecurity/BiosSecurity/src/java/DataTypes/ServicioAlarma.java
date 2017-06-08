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
public class ServicioAlarma extends Servicio{
    
    private int codAnulacion;

    public int getCodAnulacion() {
        return codAnulacion;
    }

    public void setCodAnulacion(int codAnulacion) {
        this.codAnulacion = codAnulacion;
    }
    
    public ServicioAlarma(int numServicio, Date fecha, boolean monitoreo, Propiedad propiedadCliente, int codAnulacion) {
        super(numServicio, fecha, monitoreo, propiedadCliente);
        
        setCodAnulacion(codAnulacion);
        
    }
    
}