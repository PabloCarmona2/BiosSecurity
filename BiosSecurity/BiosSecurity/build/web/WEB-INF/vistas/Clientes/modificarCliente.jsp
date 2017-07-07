<%-- 
    Document   : modificarCliente
    Created on : 06-jul-2017, 19:06:50
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Modificar Cliente">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />

        <form method="post" accept-charset="ISO-8859-1">
        <table>
            <tr>
                <td>Cédula:</td>
                <td>
                    <input type="text" name="cedula" value="${!empty cliente ? cliente.cedula : param.cedula}" readonly="readonly" id="cedula" />
                </td>
            </tr>
            <tr>
                <td>Nombre:</td>
                <td>
                    <input type="text" name="nombre" value="${!empty cliente ? cliente.nombre : param.nombre}" id="nombre" />
                </td>
            </tr>
            <tr>
                <td>Barrio:</td>
                <td>
                    <input type="text" name="barrio" value="${!empty cliente ? cliente.barrio : param.barrio}" id="barrio" />
                </td>
            </tr>
            <tr>
                <td>Direccion de cobro:</td>
                <td>
                    <input type="text" name="dirCobro" value="${!empty cliente ? cliente.dirCobro : param.dirCobro}" id="dirCobro" />
                </td>
            </tr>
            <tr>
                <td>Telefono:</td>
                <td>
                    <input type="text" name="telefono" value="${!empty cliente ? cliente.telefono : param.telefono}" id="telefono" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" name="accion" value="modificarCliente" />
                </td>
            </tr>
        </table>
    </form>

    <script>
        document.getElementById('${idFoco}').focus();
        document.getElementById('${idFoco}').select();
    </script>
        <p><a href="administrador">Volver...</a></p>  
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>
