<%-- 
    Document   : ver
    Created on : 23-jun-2017, 13:10:56
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Ver Empleado">
    <jsp:body>
        <c:if test="${!empty servicio}">
            
            <jsp:useBean id="servicio" type="DataTypes.Servicio" scope="session" />
            
            <h3><jsp:getProperty name="servicio" property="numServicio" /></h3>
            
            <h3>Dispositivos Instalados</h3>
            
            <p><a href="instalaciones?accion=instalar&numServicio=${servicio.numServicio}"><img src="imagenes/instalar.png" alt="Instalar" title="Instalar..." ></a></p>
           
            <table class="listado">
            <tr>
                <th>NUMERO DE INVENTARIO</th><th>TIPO</th><th></th>
            </tr>
            <c:if test="${servicio.getClass().simpleName == 'ServicioAlarma'}">
                        
                <c:forEach items="${servicio.alarmas}" var="dispositivo">
                    <tr>

                        <td class="texto-centro">${dispositivo.numInventario}</td>

                        <td class="texto-centro">${dispositivo.getClass().simpleName}</td>

                        <td>
                            <a href="instalaciones?accion=desinstalar&numServicio=${servicio.numServicio}&dispositivo=${dispositivo.numInventario}"><img src="imagenes/desinstalar.png" alt="Desinstalar" title="Desinstalar..." ></a>
                        </td>
                    </tr>
                </c:forEach>

            </c:if>
            <c:if test="${servicio.getClass().simpleName == 'ServicioVideoVigilancia'}">

                <c:forEach items="${servicio.camaras}" var="dispositivo">
                    <tr>

                        <td class="texto-centro">${dispositivo.numInventario}</td>

                        <td class="texto-centro">${dispositivo.getClass().simpleName}</td>

                        <td>
                            <a href="instalaciones?accion=desinstalar&numServicio=${servicio.numServicio}&dispositivo=${dispositivo.numInventario}"><img src="imagenes/desinstalar.png" alt="Desinstalar" title="Desinstalar..." ></a>
                        </td>
                    </tr>
                </c:forEach>

            </c:if>
            
        </table>
        </c:if>
        
        <p><a href="instalaciones">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>
