<%-- 
    Document   : index
    Created on : 01-jul-2017, 10:51:53
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Grabar Precios">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />

        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <c:set var="baseAlarmas" scope="page" value="${param.baseAlarmas}" />

                <c:catch var="ex">
                    <fmt:formatNumber type="number" pattern="0.00" value="${param.baseAlarmas}" var="baseAlarmas" scope="page" />
                </c:catch>

                <tr>
                    <td>Precio Base Alarmas:</td>
                    <td>
                        <input type="text" name="baseAlarmas" value="${baseAlarmas}" />
                    </td>
                </tr>
                
                <c:set var="baseCamaras" scope="page" value="${param.baseCamaras}" />

                <c:catch var="ex">
                    <fmt:formatNumber type="number" pattern="0.00" value="${param.baseCamaras}" var="baseCamaras" scope="page" />
                </c:catch>

                <tr>
                    <td>Precio Base Camaras:</td>
                    <td>
                        <input type="text" name="baseCamaras" value="${baseCamaras}" />
                    </td>
                </tr>

                <c:set var="adicionalAlarma" scope="page" value="${param.adicionalAlarma}" />

                <c:catch var="ex">
                    <fmt:formatNumber type="number" pattern="0.00" value="${param.adicionalAlarma}" var="adicionalAlarma" scope="page" />
                </c:catch>

                <tr>
                    <td>Adicional por Alarma:</td>
                    <td>
                        <input type="text" name="adicionalAlarma" value="${adicionalAlarma}" />
                    </td>
                </tr>
                
                <c:set var="adicionalCamara" scope="page" value="${param.adicionalCamara}" />
                
                <c:catch var="ex">
                    <fmt:formatNumber type="number" pattern="0.00" value="${param.adicionalCamara}" var="adicionalCamara" scope="page" />
                </c:catch>

                <tr>
                    <td>Adicional por Camara:</td>
                    <td>
                        <input type="text" name="adicionalCamara" value="${adicionalCamara}" />
                    </td>
                </tr>
                
                
                
                <tr>
                    <td>Tasa de monitoreo alarmas(%): </td>
                    <td>
                        <input type="text" name="monitoreoAlarmas" value="${param.monitoreoAlarmas}" />
                    </td>
                </tr>
                <tr>
                    <td>Tasa de monitoreo cámaras(%):</td>
                    <td>
                        <input type="text" name="monitoreoCamaras" value="${param.monitoreoCamaras}" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="Setear" />
                    </td>
                </tr>
            </table>
        </form>

        <script>
            document.getElementById('${idFoco}').focus();
            document.getElementById('${idFoco}').select();
        </script>
        <p><a href="login">Volver...</a></p>
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>
