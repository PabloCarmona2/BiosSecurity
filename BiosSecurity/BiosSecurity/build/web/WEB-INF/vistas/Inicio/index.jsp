<%-- 
    Document   : MenuPrincipal
    Created on : 18-jun-2017, 16:11:44
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Menú Principal">
    <jsp:body>
        <ul>
            <li><a href="login">Logout</a></li>
            <li><a href="empleados">Empleados</a></li>
            <li><a href="dispositivos">Dispositivos</a></li>
            <li><a href="precios">Actualizacion de Precios</a></li>
            <li><a href="servicios">Servicios</a></li>
            <li><a href="clientes">Clientes y Propiedades</a></li>
            <li><a href="instalaciones">Instalaciones de Dispositivos</a></li>
            <li><a href="recibos">Recibos</a></li>
        </ul>
    </jsp:body>
</t:PaginaMaestra>
