<%-- 
    Document   : index
    Created on : 23-jun-2017, 13:08:53
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Recibos">
    <jsp:body>
        
        <ul>
            <li><a href="recibos?accion=generar">Generar Recibos de Cobro...</a></li>
            <li><a href="recibos?accion=cobrar">Cobrar un Recibo...</a></li>
        
        </ul>
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>
