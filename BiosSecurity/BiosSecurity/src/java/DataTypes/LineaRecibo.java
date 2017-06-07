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
public class LineaRecibo {
    
    private double importe;
    private Recibo recibo;
    private Servicio servicio;

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    public LineaRecibo(double importe, Recibo recibo, Servicio servicio){
        
        setImporte(importe);
        setRecibo(recibo);
        setServicio(servicio);
        
    }
    
}
