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
        <li><a href="administrador">Administradores</a></li>
        <li><a href="tecnicos">Tecnicos</a></li>
        <li><a href="cobrador">Cobrador</a></li>
        <li><a href="servicio">Servicios</a></li>
        <li><a href="recibos">Imprimir Recibos</a></li>
        </ul>
        <t:Mensaje/>
        
    </jsp:body>
          
</t:PaginaMaestra>
