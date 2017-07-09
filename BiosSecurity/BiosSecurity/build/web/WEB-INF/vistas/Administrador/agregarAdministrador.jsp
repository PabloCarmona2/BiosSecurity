<%-- 
    Document   : agregar
    Created on : 19-jun-2017, 19:47:22
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Agregar Administrador">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />

        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Cédula:</td>
                    <td>
                        <input type="text" name="cedula" value="${param.cedula}" id="cedula" />
                    </td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td>
                        <input type="text" name="nombre" value="${param.nombre}" id="nombre" />
                    </td>
                </tr>

                <c:set var="sueldo" scope="page" value="${param.sueldo}" />

                <c:catch var="ex">
                    <fmt:formatNumber type="number" pattern="0.00" value="${param.sueldo}" var="sueldo" scope="page" />
                </c:catch>

                <tr>
                    <td>Sueldo:</td>
                    <td>
                        <input type="text" name="sueldo" value="${sueldo}" />
                    </td>
                </tr>
                <tr>
                    <td>Clave:</td>
                    <td>
                        <input type="text" name="clave" value="${param.clave}" />
                    </td>
                </tr>
                <tr>
                    <td>Fecha de Ingreso (aaaa-mm-dd):</td>
                    <td>
                        <input type="date" name="fIngreso" value="${param.fIngreso}" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="Agregar" />
                    </td>
                </tr>
            </table>
        </form>

        <script>
            document.getElementById('${idFoco}').focus();
            document.getElementById('${idFoco}').select();
        </script>
        <p><a href="administrador">Volver...</a></p>
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>