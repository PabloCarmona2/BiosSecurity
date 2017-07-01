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
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    
    public Precios Obtener() throws FileNotFoundException, IOException, Exception{
        try{
            String texto;
            String ruta = "\\WEB-INF\\Precios.txt";
            File archivo = new File(getClass().getResource(ruta).getFile());

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

                            precioAlarmas = Double.parseDouble(texto.substring(posicionDePeso - 1));

                            precios.setBaseAlarmas(precioAlarmas);

                            renglon++;

                            break;
                        case 2:

                            precioCamaras = Double.parseDouble(texto.substring(posicionDePeso - 1));

                            precios.setBaseCamaras(precioCamaras);

                            renglon++;

                            break;
                        case 3:

                            precioXAlarma = Double.parseDouble(texto.substring(posicionDePeso - 1));

                            precios.setAdicionalAlarma(precioXAlarma);

                            renglon++;

                            break;
                        case 4:

                            precioXCamara = Double.parseDouble(texto.substring(posicionDePeso - 1));

                            precios.setAdicionalCamara(precioXCamara);

                            renglon++;

                            break;
                        case 5:

                            porcentajeMonitoreoCamara = Integer.parseInt(texto.substring(posicionDePorcentaje - 1));

                            precios.setTasaCamaras(porcentajeMonitoreoCamara);

                            renglon++;

                            break;
                        case 6:

                            porcentajeMonitoreoAlarma = Integer.parseInt(texto.substring(posicionDePorcentaje - 1));

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
    
    public void Actualizar(Precios pPrecios) throws FileNotFoundException, IOException, Exception{
        try{
            
            String texto;
            String ruta = "\\Precios.txt";
            File archivo = new File(getClass().getResource(ruta).getFile());
            
            if(archivo.exists())
            {

                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                Recibo recibo;

                Precios precios = new Precios();

                int renglon = 1;
                String error = null;

                double precioAlarmas = pPrecios.getBaseAlarmas();
                double precioCamaras = pPrecios.getBaseCamaras();
                double precioXAlarma = pPrecios.getAdicionalAlarma();
                double precioXCamara = pPrecios.getAdicionalCamara();
                int porcentajeMonitoreoCamara = pPrecios.getTasaCamaras();
                int porcentajeMonitoreoAlarma = pPrecios.getTasaAlarmas();

                String lineaNueva;
                String lineaVieja;

                while((texto = br.readLine()) != null){

                    int posicionDePeso;
                    posicionDePeso = texto.indexOf("$");

                    int posicionDePorcentaje;
                    posicionDePorcentaje = texto.indexOf(")");

                    switch(renglon){
                        case 1:

                            lineaNueva = "Precio base alarmas - $ " + precioAlarmas;
                            lineaVieja = texto;

                            ModificarArchivo(archivo, lineaVieja, lineaNueva);

                            renglon++;

                            break;
                        case 2:

                            lineaNueva = "Precio base cámara - $ " + precioCamaras;
                            lineaVieja = texto;

                            ModificarArchivo(archivo, lineaVieja, lineaNueva);

                            renglon++;

                            break;
                        case 3:

                            lineaNueva = "Adicional por alarma - $ " + precioXAlarma;
                            lineaVieja = texto;

                            ModificarArchivo(archivo, lineaVieja, lineaNueva);

                            renglon++;

                            break;
                        case 4:

                            lineaNueva = "Adicional por cámara - $ " + precioXCamara;
                            lineaVieja = texto;

                            ModificarArchivo(archivo, lineaVieja, lineaNueva);

                            renglon++;

                            break;
                        case 5:

                            lineaNueva = "Tasa de monitoreo alarmas - $ " + porcentajeMonitoreoAlarma;
                            lineaVieja = texto;

                            ModificarArchivo(archivo, lineaVieja, lineaNueva);

                            renglon++;

                            break;
                        case 6:

                            lineaNueva = "Tasa de monitoreo cámaras - $ " + porcentajeMonitoreoCamara;
                            lineaVieja = texto;

                            ModificarArchivo(archivo, lineaVieja, lineaNueva);

                            renglon++;

                            break;    
                    }
                }
            }else{

                throw new Exception("No existe un fichero con los precios!.");

            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public static void EscribirEnArchivo(File archivo,String texto) throws Exception{
      try {
          
            if(!archivo.exists()){
                
               archivo.createNewFile();
               
            }

            BufferedWriter Escribir = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo, true), "utf-8"));

            Escribir.write(texto + "\r\n");

            Escribir.close();

        } catch (Exception ex) {

            throw new Exception(ex.getMessage());
        } 
    }
    
    public static  void BorrarArchivo(File archivo) throws Exception{
     try {
         if(archivo.exists()){
             
           archivo.delete(); 
           
           System.out.println("Archivo Borrado Exitosamente");
         }
     } catch (Exception ex) {
         
          throw new Exception(ex.getMessage());
          
     }
} 
    
    public static  void ModificarArchivo(File archivoViejo, String lineaVieja, String nuevaLinea) throws Exception{ 
        
        Random numaleatorio= new Random(3816L); 
        
        String nombreArchivoNuevo = archivoViejo.getParent()+ "/aux" + String.valueOf(Math.abs(numaleatorio.nextInt()))+".txt";
        
        File archivoNuevo = new File(nombreArchivoNuevo);
        
        try {
            if(archivoViejo.exists()){
                
                BufferedReader br = new BufferedReader(new FileReader(archivoViejo));
                String linea;
                
                while((linea = br.readLine())!=null) { 
                    
                    if (linea.toUpperCase().trim().equals(lineaVieja.toUpperCase().trim())) {
                        
                        EscribirEnArchivo(archivoNuevo,nuevaLinea);
                        
                    }else{
                        
                        EscribirEnArchivo(archivoNuevo, linea);
                         
                    }             
                }
                
                String nombreArchivoViejo = archivoViejo.getName();
                
                BorrarArchivo(archivoViejo);
                
                archivoNuevo.renameTo(archivoViejo);
                
                br.close();
            }else{
                
                throw new Exception("No Existe El archivo que desea modificar!.");
                
            }
        } catch (Exception ex) {
            
            throw new Exception(ex.getMessage());
            
        }
    }
}
