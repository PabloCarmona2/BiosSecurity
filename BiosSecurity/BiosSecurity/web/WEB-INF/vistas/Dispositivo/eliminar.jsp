<%-- 
    Document   : eliminarCamara
    Created on : 22-jun-2017, 15:06:37
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:PaginaMaestra titulo="Eliminar Dispositivo">
    <jsp:body>
        
        <c:if test="${!empty empleado}">
            <p>¿Confirma la eliminacion del dispositivo con el numero de inventario:  <strong>${dispositivo.numInventario}</strong> ?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="numInventario" value="${dispositivo.numInventario}" />
                <input type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
        <p><a href="dispositivos">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>


