<%-- 
    Document   : index
    Created on : 06-jul-2017, 19:06:57
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Clientes">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>BARRIO</th><th>DIRECCION DE COBRO</th><th>TELEFONO</th><th>ACCIONES</th>
            </tr>
            
            <c:forEach items="${clientes}" var="cliente">
                <tr>
                    <td class="texto-centro">${cliente.cedula}</td>
                    <td>${cliente.nombre}</td>
                    <td class="texto-centro">${cliente.barrio}</td>
                    <td class="texto-derecha">${cliente.dirCobro}</td>
                    <td class="texto-derecha">${cliente.telefono}</td>
                    <td>
                        <a href="clientes?accion=ver&cedula=${cliente.cedula}"><img src="imagenes/listar.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="clientes?accion=modificarcliente&cedula=${cliente.cedula}"><img src="imagenes/modificar.png" alt="Modificar" title="Modificar..." ></a>
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
