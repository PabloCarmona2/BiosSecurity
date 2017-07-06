<%-- 
    Document   : procesoCliente
    Created on : 06-jul-2017, 12:55:57
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Asistente Cliente">
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
                        <input type="checkbox" name="clienteInexistente" value="${param.clienteInexistente}" id="clienteInexistente" />
                    </td>
                </tr>
                <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
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
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="procesarcliente" />
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
