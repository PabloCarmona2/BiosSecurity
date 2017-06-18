/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Trabajo;

import DataTypes.Cliente;
import DataTypes.Servicio;
import Logica.Interfaces.ILogicaCliente;
import Persistencia.FabricaPersistencia;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Geronimo
 */
public class LogicaCliente implements ILogicaCliente{
    private static LogicaCliente _instancia = null;
    private LogicaCliente() { }
    public static LogicaCliente GetInstancia()
    {
        if (_instancia == null)
            _instancia = new LogicaCliente();
        return _instancia;
    }
    
    public HashMap<Cliente, List<Servicio>> ClientesYServiciosOrdenados() throws Exception{
        return FabricaPersistencia.getPersistenciaCliente().ClientesYServiciosOrdenados();
    }
}
