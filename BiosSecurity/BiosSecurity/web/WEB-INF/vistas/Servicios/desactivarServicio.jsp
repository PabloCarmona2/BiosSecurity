<%-- 
    Document   : desactivarServicio
    Created on : 02-jul-2017, 14:56:30
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Desinstalar Dispositivo">
    <jsp:body>
        <c:if test="${!empty servicio}">
            <p>Desea confirmar la desactivacion del servicio : <strong>${servicio.numServicio}</strong> de tipo: (${servicio.getClass().simpleName})?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="servicio" value="${servicio}" />
                <input type="submit" name="accion" value="Desactivar" />
            </form>
        </c:if>
        
        <p><a href="servicios">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>
