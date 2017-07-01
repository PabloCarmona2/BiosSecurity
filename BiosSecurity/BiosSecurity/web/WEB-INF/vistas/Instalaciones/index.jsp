<%-- 
    Document   : index
    Created on : 23-jun-2017, 12:36:26
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Instalaciones">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
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

                    </c:if>
                    <c:if test="${servicio.getClass().simpleName == 'ServicioVideoVigilancia'}">
                        
                            <td class="texto-centro">${servicio.numServicio}</td>

                            <td class="texto-centro">${fn:length(servicio.camaras)}</td>

                            <td class="texto-centro">${servicio.getClass().simpleName}</td>

                    </c:if>
                    
                    
                    <td>
                        <a href="instalaciones?accion=ver&numServicio=${servicio.numServicio}"><img src="imagenes/ver.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="instalaciones?accion=instalar&numServicio=${servicio.numServicio}&dispositivo=${dispositivo.numInventario}"><img src="imagenes/instalar.png" alt="Instalar" title="Instalar..." ></a>&nbsp;&nbsp;
                        <a href="instalaciones?accion=desinstalar&numServicio=${servicio.numServicio}&dispositivo=${dispositivo.numInventario}"><img src="imagenes/desinstalar.png" alt="Desinstalar" title="Desinstalar..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="login">Volver...</a></p>
        
        <t:Mensaje />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:PaginaMaestra>
