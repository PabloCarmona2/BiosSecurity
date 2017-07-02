/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Precios;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaPrecios {
    Precios Obtener(String ruta) throws FileNotFoundException, IOException, Exception;
    
    void Actualizar(Precios pPrecios, String ruta) throws FileNotFoundException, IOException, Exception;
}
