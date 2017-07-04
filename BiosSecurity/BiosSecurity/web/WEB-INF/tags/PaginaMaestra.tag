<%-- 
    Document   : PaginaMaestra
    Created on : 18-jun-2017, 15:15:59
    Author     : admin
--%>

<%@tag description="Pagina Maestra" pageEncoding="UTF-8"%>

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
            <h1>Bios Security</h1> <p style="float:right"><a href="login">LOGOUT</a></p>
            
            <h2>${titulo}</h2>
        </div>
        
        <jsp:doBody />
        
    </body>
</html>