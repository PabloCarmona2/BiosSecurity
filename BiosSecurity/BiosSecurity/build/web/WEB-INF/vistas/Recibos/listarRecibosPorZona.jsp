<%-- 
    Document   : listarRecibosPorZona
    Created on : 2/07/2017, 04:29:02 PM
    Author     : matias
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Administradores">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        <form method="post" accept-charset="ISO-8859-1">
            <select name="recibosacobrar">
                <option selected>Seleccione un barrio</option>
                   <c:forEach items="${barrios}" var="barrio">
                       
                      <option value="${barrio}">${barrio}</option>  
                       
                  </c:forEach>                              
            </select>
            <input type="submit" name="accion" value="recibosacobrar"/>
        </form>  
        <c:if test="${! empty zona}">
            <h3>${zona}</h3>
        </c:if>
        <table class="listado">
            <tr>
                <th>Numero de Recibo</th><th>NOMBRE</th><th>Total</th><th>FECHA</th><th></th>
            </tr>
            
            <c:forEach items="${recibos}" var="recibo">
                <tr>
                    <td class="texto-centro">${recibo.numRecibo}</td>
                    <td class="texto-centro">${recibo.cliente.nombre}</td>
                    <td class="texto-centro">${recibo.total}</td>
                    <td class="texto-centro">${recibo.fecha}</td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="recibos">Volver...</a></p>
     
        <t:Mensaje />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:PaginaMaestra>