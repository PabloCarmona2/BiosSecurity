<%-- 
    Document   : modificarPropiedad
    Created on : 21/06/2017, 01:06:14 AM
    Author     : matias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="en-US" />

<form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Id Propiedad:</td>
            <td>
                <input type="text" name="idProp" value="${!empty propiedad ? propiedad.idProp : param.idProp}" readonly="readonly" id="idProp" />
            </td>
        </tr>
        <tr>
            <td>Tipo:</td>
            <td>
                <input type="text" name="tipo" value="${!empty propiedad ? propiedad.tipo : param.tipo}" id="tipo" />
            </td>
        </tr>        
        <tr>
            <td>Direccion:</td>
            <td>
                <input type="text" name="direccion" value="${!empty propiedad ? propiedad.direccion : param.direccion}" id="direccion" />
            </td>
        </tr>    
         <tr>
            <td>Dueño:</td>
            <td>
                <input type="text" name="nombre" value="${!empty propiedad ? propiedad.Cliente.nombre : param.Cliente.nombre}" readonly="readonly" id="nombre" />
            </td>
         </tr>       
        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="Modificar" />
            </td>
        </tr>
    </table>
</form>

<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>
