<%-- 
    Document   : masterpage
    Created on : 18-jun-2017, 15:47:59
    Author     : Geronimo
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="titulo"%>

<%-- any content can be specified here e.g.: --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BiosSecurity - ${titulo}</title>
        
    </head>
    <body>
        <div class="cabezal">
            <h1>BiosSecurity</h1>
            
            <h2>${titulo}</h2>
        </div>
        
        <jsp:doBody />
    </body>
</html>
