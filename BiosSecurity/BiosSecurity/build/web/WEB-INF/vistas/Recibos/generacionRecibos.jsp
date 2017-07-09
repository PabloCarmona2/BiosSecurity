<%-- 
    Document   : GeneracionRecibos
    Created on : 23-jun-2017, 13:09:07
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Generacion de Recibos">
    <jsp:body>
        
        <form method="post">
            <h2>Generar los recibos correspondientes al mes seleccionado: </h2>
            
                <select name="meselejido">
                    <option selected>Seleccione un mes</option>
                       <c:forEach items="${meses}" var="mes">
                           <c:choose>
                                <c:when test = "${mes == 1}">
                                    <option value="${mes}">Enero</option>
                                </c:when>
                                <c:when test = "${mes == 2}">
                                    <option value="${mes}">Febrero</option>
                                </c:when>
                                <c:when test = "${mes == 3}">
                                    <option value="${mes}">Marzo</option>
                                </c:when>
                                <c:when test = "${mes == 4}">
                                    <option value="${mes}">Abril</option>
                                </c:when>
                                <c:when test = "${mes == 5}">
                                    <option value="${mes}">Mayo</option>
                                </c:when>
                                <c:when test = "${mes == 6}">
                                    <option value="${mes}">Junio</option>
                                </c:when>
                                <c:when test = "${mes == 7}">
                                    <option value="${mes}">Julio</option>
                                </c:when>
                                <c:when test = "${mes == 8}">
                                    <option value="${mes}">Agosto</option>
                                </c:when>
                                <c:when test = "${mes == 9}">
                                    <option value="${mes}">Septiembre</option>
                                </c:when>
                                <c:when test = "${mes == 10}">
                                    <option value="${mes}">Octubre</option>
                                </c:when>
                                <c:when test = "${mes == 11}">
                                    <option value="${mes}">Noviembre</option>
                                </c:when>
                                <c:when test = "${mes == 12}">
                                    <option value="${mes}">Diciembre</option>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                </select>
            
                <p><input type="submit" name="accion" value="Generar" /></p>
            </form>

            

        </form>
        
        <t:Mensaje />
        
        
        <p><a href="recibos">Volver...</a></p>
        
    </jsp:body>
</t:PaginaMaestra>
