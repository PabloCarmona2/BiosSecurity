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
public class Precios {
    private double baseAlarmas;
    private double baseCamaras;
    private double adicionalAlarma; 
    private double adicionalCamara; 
    private int tasaAlarmas; 
    private int tasaCamaras;

    public double getBaseAlarmas() {
        return baseAlarmas;
    }

    public void setBaseAlarmas(double baseAlarmas) {
        this.baseAlarmas = baseAlarmas;
    }

    public double getBaseCamaras() {
        return baseCamaras;
    }

    public void setBaseCamaras(double baseCamaras) {
        this.baseCamaras = baseCamaras;
    }

    public double getAdicionalAlarma() {
        return adicionalAlarma;
    }

    public void setAdicionalAlarma(double adicionalAlarma) {
        this.adicionalAlarma = adicionalAlarma;
    }

    public double getAdicionalCamara() {
        return adicionalCamara;
    }

    public void setAdicionalCamara(double adicionalCamara) {
        this.adicionalCamara = adicionalCamara;
    }

    public int getTasaAlarmas() {
        return tasaAlarmas;
    }

    public void setTasaAlarmas(int tasaAlarmas) {
        this.tasaAlarmas = tasaAlarmas;
    }

    public int getTasaCamaras() {
        return tasaCamaras;
    }

    public void setTasaCamaras(int tasaCamaras) {
        this.tasaCamaras = tasaCamaras;
    }
    
    public Precios(){
        this(1,1,1,1,1,1);
    }
    
    public Precios(double baseAlarmas,double baseCamaras ,double adicionalAlarma ,double adicionalCamara ,int monitoreoAlarmas , int monitoreoCamaras ){
        this.setBaseAlarmas(baseAlarmas);
        this.setBaseCamaras(baseCamaras);
        this.setAdicionalAlarma(adicionalAlarma);
        this.setAdicionalCamara(adicionalCamara);
        this.setTasaAlarmas(monitoreoAlarmas);
        this.setTasaCamaras(monitoreoCamaras);
    }
}