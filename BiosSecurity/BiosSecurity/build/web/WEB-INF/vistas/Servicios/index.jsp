<%-- 
    Document   : index
    Created on : 02-jul-2017, 14:56:00
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Control de Servicios">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <t:Mensaje />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <p><a href="login">Volver...</a></p>
        
        <p>
            <a href="servicios?accion=procesarCliente&tipo=camara"><img src="imagenes/instalarcamara.png" alt="Registrar Servicio VideoVigilancia" title="Registrar Servicio VideoVigilancia..." ></a>
            <a href="servicios?accion=procesarCliente&tipo=alarma"><img src="imagenes/instalaralarma.png" alt="Registrar Servicio Alarmas" title="Registrar Servicio Alarma..." ></a>
        </p>
        
        <table class="listado">
            <tr>
                <th>SERVICIO</th><th>CANTIDAD DE DISPOSITIVOS</th><th>TIPO DE SERVICIO</th><th></th>
            </tr>
            
            <c:forEach items="${servicios}" var="servicio">
                <tr>
                    <c:if test="${servicio.getClass().simpleName == 'ServicioAlarma'}">
                        
                            <td class="texto-centro">${servicio.numServicio}</td>

                            <td class="texto-centro">${fn:length(servicio.alarmas)}</td>

                            <td class="texto-centro">${servicio.getClass().simpleName}</td>
                            
                            <td>
                                <a href="servicios?accion=ver&numServicio=${servicio.numServicio}"><img src="imagenes/ver.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                                <a href="servicios?accion=desactivar&numServicio=${servicio.numServicio}"><img src="imagenes/eliminar.png" alt="Desactivar" title="Desactivar..." ></a>&nbsp;&nbsp;
                                
                            </td>

                    </c:if>
                    <c:if test="${servicio.getClass().simpleName == 'ServicioVideoVigilancia'}">
                        
                            <td class="texto-centro">${servicio.numServicio}</td>

                            <td class="texto-centro">${fn:length(servicio.camaras)}</td>

                            <td class="texto-centro">${servicio.getClass().simpleName}</td>
                            
                            <td>
                                <a href="servicios?accion=ver&numServicio=${servicio.numServicio}"><img src="imagenes/ver.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                                <a href="servicios?accion=desactivar&numServicio=${servicio.numServicio}"><img src="imagenes/eliminar.png" alt="Desactivar" title="Desactivar..." ></a>&nbsp;&nbsp;
                                
                            </td>
                    </c:if>
                    
                </tr>
            </c:forEach>
        </table>
        
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:PaginaMaestra>
