<%-- 
    Document   : agregarServicioAlarma
    Created on : 02-jul-2017, 14:10:55
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Registrar Servicio">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />
        
        <h2>Ingrese los datos correspondientes - obligatorios(*)</h2>
        
        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>(*)Monitoreo:</td>
                    <td>
                        <input type="checkbox" name="monitoreo" value="${param.monitoreo}" id="monitoreo" />
                    </td>
                </tr>
                
                <tr>
                    <td>(*)Codigo de anulacion:</td>
                    <td>
                        <input type="text" name="codAnulacion" value="${param.codAnulacion}" id="codAnulacion" />
                    </td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="registrarservicio" />
                    </td>
                </tr>
            </table>
        </form>

        <script>
            document.getElementById('${idFoco}').focus();
            document.getElementById('${idFoco}').select();
        </script>
        <p><a href="servicios">Volver...</a></p>
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>
