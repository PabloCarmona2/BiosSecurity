/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Trabajo;

import DataTypes.Precios;
import DataTypes.Recibo;
import Persistencia.Interfaces.IPersistenciaPrecios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author Geronimo
 */
public class PersistenciaPrecios implements IPersistenciaPrecios{
    
    private static PersistenciaPrecios _instancia = null;
    private PersistenciaPrecios() { }
    public static PersistenciaPrecios GetInstancia()
    {
        if (_instancia == null)
            _instancia = new PersistenciaPrecios();
        return _instancia;
    }
    
    public Precios Obtener(String ruta) throws FileNotFoundException, IOException, Exception{
        try{
            String texto;
            File archivo = new File(ruta);

            Precios precios = new Precios();

            if(archivo.exists())
            {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                Recibo recibo;

                int renglon = 1;
                String error = null;

                double precioAlarmas = 0;
                double precioCamaras = 0;
                double precioXAlarma = 0;
                double precioXCamara = 0;
                int porcentajeMonitoreoCamara = 0;
                int porcentajeMonitoreoAlarma = 0;

                while((texto = br.readLine()) != null){

                    int posicionDePeso;
                    posicionDePeso = texto.indexOf("$");

                    int posicionDePorcentaje;
                    posicionDePorcentaje = texto.indexOf(")");

                    switch(renglon){
                        case 1:

                            precioAlarmas = Double.parseDouble(texto.substring(posicionDePeso + 1).trim());

                            precios.setBaseAlarmas(precioAlarmas);

                            renglon++;

                            break;
                        case 2:

                            precioCamaras = Double.parseDouble(texto.substring(posicionDePeso + 1).trim());

                            precios.setBaseCamaras(precioCamaras);

                            renglon++;

                            break;
                        case 3:

                            precioXAlarma = Double.parseDouble(texto.substring(posicionDePeso + 1).trim());

                            precios.setAdicionalAlarma(precioXAlarma);

                            renglon++;

                            break;
                        case 4:

                            precioXCamara = Double.parseDouble(texto.substring(posicionDePeso + 1).trim());

                            precios.setAdicionalCamara(precioXCamara);

                            renglon++;

                            break;
                        case 5:

                            porcentajeMonitoreoCamara = Integer.parseInt(texto.substring(posicionDePorcentaje + 1).trim());

                            precios.setTasaCamaras(porcentajeMonitoreoCamara);

                            renglon++;

                            break;
                        case 6:

                            porcentajeMonitoreoAlarma = Integer.parseInt(texto.substring(posicionDePorcentaje + 1).trim());

                            precios.setTasaAlarmas(porcentajeMonitoreoAlarma);

                            renglon++;

                            break;    
                    }
                }
            }else{

                throw new Exception("No existe un fichero con los precios!.");

            }
            return precios;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public void Actualizar(Precios pPrecios, String ruta) throws FileNotFoundException, IOException, Exception{
        
        FileWriter fw = null;
        PrintWriter pw = null;
        
        try{
            double precioAlarmas = pPrecios.getBaseAlarmas();
            double precioCamaras = pPrecios.getBaseCamaras();
            double precioXAlarma = pPrecios.getAdicionalAlarma();
            double precioXCamara = pPrecios.getAdicionalCamara();
            int porcentajeMonitoreoCamara = pPrecios.getTasaCamaras();
            int porcentajeMonitoreoAlarma = pPrecios.getTasaAlarmas();
            
            fw = new FileWriter(ruta);
            
            pw= new PrintWriter(fw);
            pw.println("Precio base alarmas - $ " + precioAlarmas);
            pw.println("Precio base camaras - $ " + precioCamaras);
            pw.println("Adicional por alarma - $ " + precioXAlarma);
            pw.println("Adicional por camara - $ " + precioXCamara);
            pw.println("Tasa de monitoreo alarmas - (%) " + porcentajeMonitoreoAlarma);
            pw.println("Tasa de monitoreo camaras - (%) " + porcentajeMonitoreoCamara);
            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }finally {

            try {
                if (pw != null) {
                    pw.close();
                }

                if (fw != null) {
                    fw.close();
                }
            } catch (Exception ex) {
                throw new Exception("¡ERROR! Ocurrió un error al cerrar los recursos.");
            }
        }
        
    }
}
