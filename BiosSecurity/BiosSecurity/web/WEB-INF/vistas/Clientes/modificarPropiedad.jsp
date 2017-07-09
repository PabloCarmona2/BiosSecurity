<%-- 
    Document   : modificarPropiedad
    Created on : 21/06/2017, 01:06:14 AM
    Author     : matias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en-US" />
<t:PaginaMaestra titulo="Modificar Propiedad">
    <jsp:body>
        
        <fmt:setLocale value="en-US" />
        
        
        
        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Numero de propiedad :</td>
                    <td>
                        <input type="text" name="numPropiedad" value="${!empty propiedad ? propiedad.idProp : param.idProp}" readonly="readonly" id="numPropiedad" />
                    </td>
                </tr>
                <tr>
                    <td>Tipo:</td>
                    <td>
                        <input type="text" name="tipoPropiedad" value="${!empty propiedad ? propiedad.tipo : param.tipoPropiedad}" id="tipoPropiedad" />
                    </td>
                </tr>
                <tr>
                    <td>Direccion:</td>
                    <td>
                        <input type="text" name="direccion" value="${!empty propiedad ? propiedad.direccion : param.direccion}" id="direccion"/>
                    </td>
                </tr>
                <tr>
                    <td>Cliente:</td>
                    <td>
                        <input type="text" name="cedula" value="${!empty cliente ? cliente.cedula : param.cedula}" readonly="readonly" id="cedula"/>
                    </td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="accion" value="modificarPropiedad" />
                    </td>
                </tr>
            </table>
        </form>

        <p><a href="clientes">Volver...</a></p>
        <t:Mensaje />
        
    </jsp:body>
</t:PaginaMaestra>