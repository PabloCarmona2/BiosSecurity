<%-- 
    Document   : rellenarAlarma
    Created on : 23-jun-2017, 13:25:09
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

            <input type="hidden" name="dispositivo" value="${dispositivo}" />
            <input type="hidden" name="servicio" value="${servicio}" />

            <table>
                <tr>
                    <td>Descripcion de Ubicacion:</td>
                    <td>
                        <input type="text" name="descripcion" value="${!empty dispositivo ? dispositivo.descripcionUbicacion : param.descripcionUbicacion}" id="descripcionUbicacion" />
                    </td>
                </tr>
                <tr>
                    <td>Instalador:</td>
                    <td>
                        <input type="text" name="instalador" value="${empleado.nombre}" id="empleado" />
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
        
        <p><a href="instalaciones">Volver...</a></p>
    </jsp:body>
</t:PaginaMaestra>