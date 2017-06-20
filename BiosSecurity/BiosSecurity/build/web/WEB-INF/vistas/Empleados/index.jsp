<%-- 
    Document   : index
    Created on : 19-jun-2017, 15:55:28
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Empleados">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        
        <form method="post">
            
            <select name="tipo" size="1">
                <option value="nulo">Seleccionar...</option>
                <option value="tecnico">Tecnicos</option>
                <option value="cobrador">Cobradores</option>
                <option value="administrador">Administradores</option>
            </select>
            
            <input type="submit" name="accion" value="Seleccionar" />
            
        </form>
        
    </jsp:body>
</t:PaginaMaestra>

