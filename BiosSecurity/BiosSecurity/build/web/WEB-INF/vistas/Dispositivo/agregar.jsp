<%-- 
    Document   : agregarAlarma
    Created on : 22-jun-2017, 15:06:15
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%-- any content can be specified here e.g.: --%>
<fmt:setLocale value="en-US" />


<t:PaginaMaestra titulo="Agregar Dispositivo">
    <jsp:body>
        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Tipo: Camara(Marcado)/Alarma(Desmarcado)</td>
                    <input type="checkbox" name="tipoDispositivo" value="Camara">
                <tr>
                    <td>
                        <input type="submit" name="accion" value="Agregar" />
                    </td>
                </tr>
            </table>
        </form>
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>


