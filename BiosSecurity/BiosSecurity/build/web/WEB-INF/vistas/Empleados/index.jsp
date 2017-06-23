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
        
        <ul>
            <li><a href="tecnicos?accion=index">Tecnicos...</a></li>
            <li><a href="administradores?accion=index">Administradores...</a></li>
            <li><a href="cobradores?accion=index">Cobradores...</a></li>
        </ul>
        
    </jsp:body>
</t:PaginaMaestra>

