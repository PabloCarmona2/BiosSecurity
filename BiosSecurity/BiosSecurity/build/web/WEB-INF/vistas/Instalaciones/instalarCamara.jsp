<%-- 
    Document   : instalarAlarma
    Created on : 23-jun-2017, 13:10:34
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Instalaciones">
    <jsp:body>
        <fmt:setLocale value="en-US" />

        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Numero de Servicio:</td>
                    <td>
                        <input tyspe="text" name="numServicio" value="${!empty servicio ? servicio.numServicio : param.numServicio}" readonly="readonly" id="numServicio" />
                    </td>
                </tr>
                <tr>
                    <td>Numero de Inventario del Dispositivo:</td>
                    <td>
                        <input type="text" name="numInventario" value="${param.numInventario}" id="nombre" />
                    </td>
                </tr>
                <tr>
                    <td>Descripcion de Ubicacion:</td>
                    <td>
                        <input type="text" name="descripcion" value="${param.descripcionUbicacion}" id="descripcionUbicacion" />
                    </td>
                </tr>
                <tr>
                    <td>Instalador:</td>
                    <td>
                        <input type="text" name="instalador" value="${empleadoLogueado.cedula}" readonly="readonly" id="empleado" />
                    </td>
                </tr>
                <tr>
                    <td>Exterior?:</td>
                    <td>
                        <input type="checkbox" name="exterior" value="exterior">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="Instalar"/>
                    </td>
                </tr>
                
            </table>
        </form>
        <p><a href="instalaciones">Volver...</a></p>
    </jsp:body>
</t:PaginaMaestra>
