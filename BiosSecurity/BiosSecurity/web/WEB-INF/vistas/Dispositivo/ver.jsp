<%-- 
    Document   : verCamara
    Created on : 22-jun-2017, 16:10:55
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Ver Dispositivo">
    <jsp:body>
        <c:if test="${!empty dispositivo}">
            <jsp:useBean id="dispositivo" type="DataTypes.Dispositivo" scope="request" />
            
            
            <ul>
                <li><strong>NumInventario:</strong> <jsp:getProperty name="dispositivo" property="numInventario" /></li>
                    
                <c:if test="${!empty dispositivo.instalador}">
                    <li><strong>Instalador:</strong> Actualmente instalado en un servicio</li>
                </c:if>
                <c:if test="${empty dispositivo.instalador}">
                    <li><strong>Instalador:</strong> No se ha instalado este dispositivo</li>
                </c:if>
            
            </ul>
        </c:if>
        
        <p><a href="dispositivos">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>
