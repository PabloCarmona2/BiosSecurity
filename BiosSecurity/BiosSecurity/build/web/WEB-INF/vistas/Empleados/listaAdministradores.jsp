<%-- 
    Document   : listaAdministradores
    Created on : 20-jun-2017, 15:39:08
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Empleados">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <p><a href="tecnicos?accion=agregar"><img src="imagenes/glyphicons-191-plus-sign.png" alt="Agregar" title="Agregar..." ></a></p>
        
        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>SUELDO</th><th>FECHA INGRESO</th><th></th>
            </tr>
            
            <c:forEach items="${empleados}" var="empleado">
                <tr>
                    <td class="texto-centro">${empleado.cedula}</td>
                    <td>${empleado.nombre}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${empleado.sueldo}" />
                    </td>
                    <td class="texto-derecha">${empleado.fIngreso}</td>
                    <td>
                        <a href="admiistradores?accion=ver&cedula=${empleado.cedula}"><img src="imagenes/glyphicons-52-eye-open.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="administradores?accion=modificar&cedula=${empleado.cedula}"><img src="imagenes/glyphicons-31-pencil.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="administradores?accion=eliminar&cedula=${empleado.cedula}"><img src="imagenes/glyphicons-192-minus-sign.png" alt="Eliminar" title="Eliminar..." ></a>
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