<%-- 
    Document   : instalarAlarma
    Created on : 23-jun-2017, 13:10:34
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="en-US" />

<form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Numero de Servicio:</td>
            <td>
                <input type="text" name="numServicio" value="${!empty servicio ? servicio.numServicio : param.numServicio}" readonly="readonly" id="numServicio" />
            </td>
        </tr>
        <tr>
            <td>Numero de Inventario del Dispositivo:</td>
            <td>
                <input type="text" name="numInventario" value="${param.numInventario}" id="nombre" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="Rellenar"/>
            </td>
        </tr>
    </table>
</form>
