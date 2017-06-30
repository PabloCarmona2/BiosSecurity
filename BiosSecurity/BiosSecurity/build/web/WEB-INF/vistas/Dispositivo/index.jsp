<%-- 
    Document   : index
    Created on : 22-jun-2017, 15:05:45
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Dispositivos">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <p><a href="dispositivos?accion=agregar"><img src="imagenes/agregar.png" alt="Agregar" title="Agregar..." ></a></p>
        
        <table class="listado">
            <tr>
                <th>NUMERO de INVENTARIO</th><th>TIPO</th><th></th>
            </tr>
            
            <c:forEach items="${dispositivos}" var="dispositivo">
                <tr>
                    <td class="texto-centro">${dispositivo.numInventario}</td>
                    <c:choose>
                        <c:when test = "${dispositivo.class.name} == 'Camara'">
                            <td class="texto-centro">Camara</td>
                        </c:when>
                        <c:otherwise>
                            <td class="texto-centro">Alarma</td>
                        </c:otherwise>
                    </c:choose>
                    
                    <td>
                        <a href="dispositivo?accion=ver&numInventario=${dispositivo.numInventario}"><img src="imagenes/ver.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="dispositivo?accion=eliminar&numInventario=${dispositivo.numInventario}"><img src="imagenes/eliminar.png" alt="Eliminar" title="Eliminar..." ></a>
                        
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio">Volver...</a></p>
        
        <t:Mensaje />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:PaginaMaestra>
