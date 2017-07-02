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
            <li><a href="listaTecnicos.jsp">Tecnicos...</a></li>
            <li><a href="listaAdministradores.jsp">Administradores...</a></li>
            <li><a href="listaCobradores.jsp">Cobradores...</a></li>
        </ul>
        
        <p><a href="login">Volver...</a></p>
        
    </jsp:body>
</t:PaginaMaestra>

