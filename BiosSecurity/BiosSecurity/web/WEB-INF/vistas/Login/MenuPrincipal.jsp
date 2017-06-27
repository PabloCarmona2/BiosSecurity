<%-- 
    Document   : MenuPrincipal
    Created on : 25/06/2017, 03:58:48 PM
    Author     : matias
--%>

 <%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:PaginaMaestra titulo="Menú Principal">
    <jsp:body>
        <ul>
        <li><a href="administrador">Control de Administradores</a></li>
        <li><a href="tecnicos">Control de Tecnicos</a></li>
        <li><a href="cobradores">Control de Cobradores</a></li>
        <li><a href="instalaciones">Instalaciones...</a></li>
        <li><a href="servicio">Servicios</a></li>
        <li><a href="recibos">Funciones para Recibos</a></li>
        </ul>
        <t:Mensaje/>
        
    </jsp:body>
          
</t:PaginaMaestra>
