<%-- 
    Document   : PaginaMaestra
    Created on : 18-jun-2017, 15:15:59
    Author     : admin
--%>

<%@tag description="Pagina Maestra" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="titulo"%>

<%-- any content can be specified here e.g.: --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bios Security - ${titulo}</title>
        
        <link rel="stylesheet" href="css/general.css" />
    </head>
    <body>
        <div class="cabezal">
            <img src="imagenes/Banner.png" alt="Bios Security">
            
            <h2>${titulo}</h2>
            <c:if test="${!empty empleadoLogueado}">
                <h3 style="color: white">Tipo de sesion: ${empleadoLogueado.getClass().simpleName} - Usuario: ${empleadoLogueado.nombre} (${empleadoLogueado.cedula})</h3>
                <p style="float:right" style="color: whitesmoke"><a href="login?accion=logout">LOGOUT</a></p>
            </c:if>
        </div>
        
        <jsp:doBody />
        
    </body>
</html>