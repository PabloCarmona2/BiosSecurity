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
        
        <form method="post">
            <h2>Generar los recibos correspondientes al mes actual: </h2>
            <h2>${mes}</h2>

            <p><input type="submit" name="accion" value="Generar" /></p>

        </form>
        
        <t:Mensaje />
        
        
        <p><a href="recibos">Volver...</a></p>
        
    </jsp:body>
</t:PaginaMaestra>
