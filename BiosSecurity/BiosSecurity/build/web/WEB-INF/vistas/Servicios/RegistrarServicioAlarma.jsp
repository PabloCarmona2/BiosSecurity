<%-- 
    Document   : agregarServicioAlarma
    Created on : 02-jul-2017, 14:10:55
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Agregar Servicio">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />
        
        <h2>Ingrese los datos correspondientes - obligatorios(*)</h2>
        
        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>(*)Numero de documento del Cliente:</td>
                    <td>
                        <input type="text" name="cedulaCliente" value="${param.cedulaCliente}" id="cedulaCliente" />
                    </td>
                </tr>
                <tr>
                    <td>Cliente inexistente?:(rellene los campos de cliente (Rojos) en caso de marcar esta casilla)</td>
                    <td>
                        <input type="check" name="clienteInexistente" value="${param.clienteInexistente}" id="clienteInexistente" />
                    </td>
                </tr>
                <tr>
                    <td style="color:red">(cliente)Nombre:</td>
                    <td>
                        <input type="text" name="nombreCliente" value="${param.nombreCliente}" id="nombreCliente" />
                    </td>
                </tr>
                <tr>
                    <td style="color:red">(cliente)Barrio:</td>
                    <td>
                        <input type="text" name="barrioCliente" value="${param.barrioCliente}" id="barrioCliente" />
                    </td>
                </tr>
                <tr>
                    <td style="color:red">(cliente)Direccion de cobro:</td>
                    <td>
                        <input type="text" name="dirCobro" value="${param.dirCobro}" id="dirCobro" />
                    </td>
                </tr>
                <tr>
                    <td style="color:red">(cliente)Telefono:</td>
                    <td>
                        <input type="text" name="telefonoCliente" value="${param.telefonoCliente}" id="telefonoCliente" />
                    </td>
                </tr>
                <tr>
                    <td style="color:red">(cliente)Telefono:</td>
                    <td>
                        <input type="text" name="telefonoCliente" value="${param.telefonoCliente}" id="telefonoCliente" />
                    </td>
                </tr>
                <tr>
                    <td>(*)Numero de propiedad :</td>
                    <td>
                        <input type="text" name="cedulaCliente" value="${param.cedulaCliente}" id="cedulaCliente" />
                    </td>
                </tr>
                <tr>
                    <td>Propiedad inexistente?:(rellene los campos de propiedad (Verde) en caso de marcar esta casilla)</td>
                    <td>
                        <input type="check" name="propiedadInexistente" value="${param.propiedadInexistente}" id="propiedadInexistente" />
                    </td>
                </tr>
                <tr>
                    <td style="color:green">(propiedad)Tipo:</td>
                    <td>
                        <input type="text" name="tipoPropiedad" value="${param.tipoPropiedad}" id="tipoPropiedad" />
                    </td>
                </tr>
                <tr>
                    <td style="color:green">(propiedad)Direccion:</td>
                    <td>
                        <input type="text" name="direccionPropiedad" value="${param.direccionPropiedad}" id="direccionPropiedad" />
                    </td>
                </tr>
                <tr>
                    <td>(*)Monitoreo:</td>
                    <td>
                        <input type="checkbox" name="monitoreo" value="${param.monitoreo}" id="monitoreo" />
                    </td>
                </tr>
                
                <tr>
                    <td>(*)Codigo de anulacion:</td>
                    <td>
                        <input type="text" name="codAnulacion" value="${param.codAnulacion}" id="codAnulacion" />
                    </td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="Registrar" />
                    </td>
                </tr>
            </table>
        </form>

        <script>
            document.getElementById('${idFoco}').focus();
            document.getElementById('${idFoco}').select();
        </script>
        <p><a href="servicios">Volver...</a></p>
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>
