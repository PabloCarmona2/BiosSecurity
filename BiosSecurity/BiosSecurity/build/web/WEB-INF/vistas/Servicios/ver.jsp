<%-- 
    Document   : ver
    Created on : 02-jul-2017, 15:36:03
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Ver Empleado">
    <jsp:body>
        
            <h3>Servicio (info Extendida)</h3>
            
            <p><a href="servicios?accion=desactivar&numServicio=${servicio.numServicio}"><img src="imagenes/eliminar.png" alt="Desactivar" title="Desactivar..." ></a></p>
            
            <table class="listado">
            <tr>
                <th>NUMERO DE SERVICIO</th><th>TIPO</th><th>FECHA DE INICIADO</th><th>MONITOREO?</th><th>PROPIEDAD(Cliente)</th><th>CANTIDAD DE DISPOSITIVOS INSTALADOS</th><th>Info Extra</th><th></th>
            </tr>
            <c:if test="${servicio.getClass().simpleName == 'ServicioAlarma'}">
                <tr>

                    <td class="texto-centro">${servicio.numServicio}</td>

                    <td class="texto-centro">${servicio.getClass().simpleName}</td>
                    
                    <td class="texto-centro"><fmt:formatDate value="${servicio.fecha}" pattern="dd-MM-yyyy"/></td>
                    
                    <c:choose>
                        <c:when test = "${servicio.monitoreo}">
                            <td class="texto-centro">Activo</td>
                        </c:when>
                        <c:otherwise>
                            <td class="texto-centro">InActivo</td>
                        </c:otherwise>
                    </c:choose>
                            
                    <td class="texto-centro">${servicio.propiedadCliente.direccion}(${servicio.propiedadCliente.dueño.nombre})</td>
                    
                    <td class="texto-centro">${fn:length(servicio.alarmas)}</td>

                    <td class="texto-centro">${servicio.codAnulacion}</td>
                    
                </tr>

            </c:if>
            <c:if test="${servicio.getClass().simpleName == 'ServicioVideoVigilancia'}">

                <tr>

                    <td class="texto-centro">${servicio.numServicio}</td>

                    <td class="texto-centro">${servicio.getClass().simpleName}</td>
                    
                    <td class="texto-centro"><fmt:formatDate value="${servicio.fecha}" pattern="dd-MM-yyyy"/></td>
                    
                    <c:choose>
                        <c:when test = "${servicio.monitoreo}">
                            <td class="texto-centro">Activo</td>
                        </c:when>
                        <c:otherwise>
                            <td class="texto-centro">InActivo</td>
                        </c:otherwise>
                    </c:choose>
                            
                    <td class="texto-centro">${servicio.propiedadCliente.direccion}(${servicio.propiedadCliente.dueño.nombre})</td>
                    
                    <td class="texto-centro">${fn:length(servicio.camaras)}</td>

                    <td class="texto-centro">${servicio.terminal}</td>
                    
                </tr>

            </c:if>
            
        </table>
            
        <p><a href="servicios">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>

