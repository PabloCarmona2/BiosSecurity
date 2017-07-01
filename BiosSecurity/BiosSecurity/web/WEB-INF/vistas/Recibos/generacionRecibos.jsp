<%-- 
    Document   : GeneracionRecibos
    Created on : 23-jun-2017, 13:09:07
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Generacion de Recibos">
    <jsp:body>
        
        <c:set var = "now" value = "<% = new java.util.Date()%>" />
        <h2>Generar los recibos correspondientes al mes actual: <fmt:formatDate pattern = "MM" value = "${now}"/></h2>
        
        <p><input type="submit" name="accion" value="Generar" /></p>
        
        <t:Mensaje />
        
        
        <p><a href="recibos">Volver...</a></p>
        
    </jsp:body>
</t:PaginaMaestra>
