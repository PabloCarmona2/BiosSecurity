/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Cobrador;
<<<<<<< HEAD
import DataTypes.Recibo;
import java.util.List;
=======
>>>>>>> 7a98a246124cda750f9f6dcf42e6a3fb80c0874d

/**
 *
 * @author Geronimo
 */
public interface IPersistenciaCobrador {
<<<<<<< HEAD
    List<Recibo> RecibosaCobrar(String zona) throws Exception;
    Cobrador Buscar(int cedula) throws Exception;
=======
    
    Cobrador LoginCobrador(int cedula, String clave)throws Exception;
    
>>>>>>> 7a98a246124cda750f9f6dcf42e6a3fb80c0874d
}
