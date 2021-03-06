<%-- 
    Document   : desinstalarAlarma
    Created on : 23-jun-2017, 13:10:19
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Desinstalar Dispositivo">
    <jsp:body>
        <c:if test="${!empty servicio}">
            <p>Desea confirmar la desinstalacion del dispositivo : <strong>${dispositivo.numInventario}</strong> de tipo: (${dispositivo.getClass().simpleName}) del servicio: <strong>${servicio.numServicio}</strong>?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="servicio" value="${servicio.numServicio}" />
                <input type="hidden" name="dispositivo" value="${dispositivo.numInventario}" />
                <input type="submit" name="accion" value="Desinstalar" />
            </form>
        </c:if>
        
        <p><a href="instalaciones">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>
        
