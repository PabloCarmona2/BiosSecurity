<%-- 
    Document   : verTecnico
    Created on : 19-jun-2017, 20:14:35
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Ver Empleado">
    <jsp:body>
        <c:if test="${!empty empleado}">
            <jsp:useBean id="empleado" type="DataTypes.Tecnico" scope="request" />
            
            <h3><jsp:getProperty name="empleado" property="nombre" /></h3>
            
            <ul>
                <li><strong>Cédula:</strong> <jsp:getProperty name="empleado" property="cedula" /></li>
                <li><strong>Nombre:</strong> <jsp:getProperty name="empleado" property="nombre" /></li>
                <li><strong>Clave:</strong> <jsp:getProperty name="empleado" property="clave" /></li>
                <li><strong>Fecha de ingreso:</strong> <jsp:getProperty name="empleado" property="fIngreso" /></li>
                <li><strong>Sueldo:</strong> <jsp:getProperty name="empleado" property="sueldo" /></li>
                <li><strong>Especializacion:</strong> <jsp:getProperty name="empleado" property="especializacion" /></li>
            </ul>
        </c:if>
        
        <p><a href="tecnicos">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>