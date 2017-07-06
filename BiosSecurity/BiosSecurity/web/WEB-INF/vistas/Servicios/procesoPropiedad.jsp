<%-- 
    Document   : procesoPropiedad
    Created on : 06-jul-2017, 12:56:18
    Author     : Geronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Asistente Propiedad">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />
        
        <h2>Ingrese los datos correspondientes - obligatorios(*)</h2>
        
        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>(*)Numero de propiedad :</td>
                    <td>
                        <input type="text" name="numPropiedad" value="${param.numPropiedad}" id="numPropiedad" />
                    </td>
                </tr>
                <tr>
                    <td>Propiedad inexistente?:(rellene los campos de propiedad (Verde) en caso de marcar esta casilla)</td>
                    <td>
                        <input type="checkbox" name="propiedadInexistente" value="${param.propiedadInexistente}" id="propiedadInexistente" />
                    </td>
                </tr>
                <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
                <tr>
                    <td style="color:green">(propiedad)Tipo:</td>
                    <td>
                        <input type="text" name="tipoPropiedad" value="${param.tipoPropiedad}" id="tipoPropiedad" />
                    </td>
                </tr>
                <tr>
                    <td style="color:green">(propiedad)Direccion:</td>
                    <td>
                        <input type="text" name="direccionPropiedad" value="${param.direccionPropiedad}" id="direccionPropiedad"/>
                    </td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="procesarpropiedad" />
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
