<%-- 
    Document   : agregarServicioVideo
    Created on : 02-jul-2017, 14:11:41
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Agregar Servicio">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />

        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Numero de propiedad del Cliente:</td>
                    <td>
                        <input type="text" name="idprop" value="${param.IdProp}" id="nombre" />
                    </td>
                </tr>
                
                <tr>
                    <td>Monitoreo:</td>
                    <td>
                        <input type="checkbox" name="monitoreo" value="${param.monitoreo}" />
                    </td>
                </tr>
                
                <tr>
                    <td>Terminal:</td>
                    <td>
                        <input type="checkbox" name="terminal" value="${param.terminal}" />
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
        <p><a href="Servicio">Volver...</a></p>
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>
