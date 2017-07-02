/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Interfaces;

import DataTypes.Cobrador;
import DataTypes.Recibo;
import java.util.List;
/**
 *
 * @author Geronimo
 */
public interface IPersistenciaCobrador {
    Cobrador Buscar(int cedula) throws Exception;
    void AgregarCobrador(Cobrador Cob) throws Exception;
    Cobrador LoginCobrador(int cedula, String clave)throws Exception;
    void EliminarCobrador(Cobrador Cob) throws Exception;
    void EditarCobrador(Cobrador Cob) throws Exception;
}
