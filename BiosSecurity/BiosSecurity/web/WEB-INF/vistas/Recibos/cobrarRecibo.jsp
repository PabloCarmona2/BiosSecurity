<%-- 
    Document   : cobrarRecibo
    Created on : 23-jun-2017, 13:09:16
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Cobro de Recibo">
    <jsp:body>
        
        <h2>Cobrar un recibo mediante su numero de recibo.</h2>
        <form method="post">
            <p><input type="text" name="numRecibo" value="${param.buscar}"</p>
        
            <p><input type="submit" name="accion" value="Cobrar" /></p>
        </form>
        
        <p><a href="recibos">Volver...</a></p>
        
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>