<%-- 
    Document   : Login
    Created on : 11-jun-2017, 18:18:46
    Author     : admin
--%>


 <%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:PaginaMaestra titulo="Menú Principal">
    <jsp:body>
        
        <t:Mensaje/>
        <h1>Ingrese cedula y contraseña:</h1>
        <form method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Cedula: </td><td><input type="text" name="cedula" /></td>
                </tr>
                <tr>
                    <td>Contraseña: </td><td><input type="text" name="clave" /></td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" name="accion" value="login" /></td>
                </tr>
            </table>
        </form>
    </jsp:body>
          
</t:PaginaMaestra>
