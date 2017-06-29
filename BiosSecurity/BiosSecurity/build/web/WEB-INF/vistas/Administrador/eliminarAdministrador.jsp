<%-- 
    Document   : eliminarAdmin
    Created on : 19-jun-2017, 20:13:48
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Eliminar Empleado">
    <jsp:body>
        <c:if test="${!empty empleado}">
            <p>Desea confirmar la eliminacion del empleado con la cedula: <strong>${empleado.cedula}</strong> y el nombre: (${empleado.nombre})?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="cedula" value="${empleado.cedula}" />
                <input type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
         <p><a href="administrador">Volver...</a></p>
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>
