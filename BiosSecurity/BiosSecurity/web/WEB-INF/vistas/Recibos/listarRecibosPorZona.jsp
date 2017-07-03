<%-- 
    Document   : listarRecibosPorZona
    Created on : 2/07/2017, 04:29:02 PM
    Author     : matias
--%>
<%--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Administradores">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form method="post">
        <input list="barrios" name="barrios">
            <datalist id="barrios">
                <c:forEach items="${recibosGenerales}" var="recibo">
                <option href="administrador?accion=RecibosaCobrar&barrio=${recibo.Cliente.barrio}">
                </c:forEach>
            </datalist>
        <input type="submit">
        </form>
              
        
        <p><a href="login?accion=login">Volver...</a></p>
     
        <t:Mensaje />
        
        <script>
            document.getElementById('barrios').focus();
            document.getElementById('barrios').select();
        </script>
    </jsp:body>
</t:PaginaMaestra>
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
                   <c:forEach items="${barrios}" var="barrio">
                       <option>${barrio}</option>                                                
                   </c:forEach>                              
            </select>
            <input type="submit" name="accion" value="recibosacobrar"/>
        </form>
        
            
      
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
        
        <p><a href="login?accion=login">Volver...</a></p>
     
        <t:Mensaje />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:PaginaMaestra>