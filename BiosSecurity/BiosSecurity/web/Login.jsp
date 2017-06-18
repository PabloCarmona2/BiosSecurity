<%-- 
    Document   : Login
    Created on : 11-jun-2017, 18:18:46
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Menú Principal">
    <jsp:body>
        <jsp:useBean id="producto" class="DataTypes.Empleado" scope="session">
            
        </jsp:useBean>
        
        <h1>Ingrese cedula y contraseña:</h1>
        <form action="login" method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Cedula: </td><td><input type="text" name="cedula" /></td>
                </tr>
                <tr>
                    <td>Contraseña: </td><td><input type="text" name="clave" /></td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" name="Login" value="Login" /></td>
                </tr>
            </table>
        </form>
        
        <t:mensaje />
    </jsp:body>
</t:PaginaMaestra>
