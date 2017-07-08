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
        <c:if test="${!empty numRecibo}">
            
            <form method="post">
                <p><input type="text" name="numRecibo" value="${!empty numRecibo ? numRecibo : param.numRecibo}" readonly="readonly"</p>

                <p><input type="submit" name="accion" value="Cobrar" /></p>
            </form>
                
        </c:if>
        
        
        <p><a href="recibos">Volver...</a></p>
        
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>