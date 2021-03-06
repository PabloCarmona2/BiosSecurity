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
public class Recibo {
    
    private Integer numRecibo;
    private Date fecha;
    private double total;
    private Cliente cliente;
    private Cobrador cobrador;
    private boolean cobrado;
    private List<LineaRecibo> lineas;

    public List<LineaRecibo> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaRecibo> lineas) {
        this.lineas = lineas;
    }

    public Integer getNumRecibo() {
        return numRecibo;
    }

    public void setNumRecibo(Integer numRecibo) {
        this.numRecibo = numRecibo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }

    public boolean isCobrado() {
        return cobrado;
    }

    public void setCobrado(boolean cobrado) {
        this.cobrado = cobrado;
    }
    
    public Recibo(){
        this(0, null, 0, null, null, false, null);
    }
    
    public Recibo(Integer numRecibo, Date fecha, double total, Cliente cliente, Cobrador cobrador, boolean cobrado, List<LineaRecibo> lineas){
        
        setNumRecibo(numRecibo);
        setFecha(fecha);
        setTotal(total);
        setCliente(cliente);
        setCobrador(cobrador);
        setCobrado(cobrado);
        setLineas(lineas);
    }
    
}
