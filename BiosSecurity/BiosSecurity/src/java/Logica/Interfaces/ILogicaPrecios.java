/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Interfaces;

import DataTypes.Precios;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Geronimo
 */
public interface ILogicaPrecios {
    Precios Obtener() throws FileNotFoundException, IOException, Exception;
    
    void Actualizar(Precios pPrecios) throws FileNotFoundException, IOException, Exception;
}
