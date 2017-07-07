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
        
        <c:if test="${empleadoLogueado.getClass().simpleName == 'Administrador'}">
            <ul>
                <li><a href="administrador">Control de Administradores</a></li>
                <li><a href="tecnicos">Control de Tecnicos</a></li>
                <li><a href="cobradores">Control de Cobradores</a></li>
                <li><a href="clientes">Control de Clientes y Propiedades</a></li>
                <li><a href="servicios">Control de Servicios</a></li>
                <li><a href="dispositivos">Control de Dispositivos</a></li>
                <li><a href="precios">Declaracion de Precios</a></li>
                <li><a href="recibos">Funciones de Recibos</a></li>
            </ul>
            
        </c:if>
        <c:if test="${empleadoLogueado.getClass().simpleName == 'Tecnico'}">
            <ul>
                <li><a href="instalaciones">Instalacion y Desinstalacion de Dispositivos en/de Servicios</a></li>
            </ul>
            
        </c:if>
        <c:if test="${empleadoLogueado.getClass().simpleName == 'Cobrador'}">
            <ul>
                <li><a href="recibos">Funciones de Recibos</a></li>
            </ul>
        </c:if>    
            
        
        <t:Mensaje/>
        
    </jsp:body>
          
</t:PaginaMaestra>
