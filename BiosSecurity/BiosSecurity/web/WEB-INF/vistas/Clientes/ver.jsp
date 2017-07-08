<%-- 
    Document   : ver
    Created on : 06-jul-2017, 19:07:08
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Ver Propiedades del cliente">
    <jsp:body>
        <c:if test="${!empty cliente}">
            
            <c:if test="${!empty propiedades}">
                
                <jsp:useBean id="cliente" type="DataTypes.Cliente" scope="request" />
            
                <h3>De nombre: <jsp:getProperty name="cliente" property="nombre" /></h3>
                <h4>Con el numero de documento: <jsp:getProperty name="cliente" property="cedula" /></h4>
                
                <table class="listado">
                    <tr>
                        <th>ID</th><th>TIPO</th><th>DIRECCION</th><th></th>
                    </tr>

                    <c:forEach items="${propiedades}" var="propiedad">
                        <tr>
                            <td class="texto-centro">${propiedad.idProp}</td>
                            <td>${propiedad.tipo}</td>
                            <td class="texto-derecha">${propiedad.direccion}</td>
                            <td>
                                <a href="clientes?accion=modificarPropiedad&idProp=${propiedad.idProp}&cedulaCliente=${cliente.cedula}"><img src="imagenes/modificar.png" alt="Modificar" title="Modificar..." ></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                
            </c:if>
            
        </c:if>
        
        <p><a href="clientes">Volver...</a></p>
        
        <t:Mensaje />
    </jsp:body>
</t:PaginaMaestra>
