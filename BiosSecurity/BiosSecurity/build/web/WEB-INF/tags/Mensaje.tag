<%-- 
    Document   : Mensaje
    Created on : 18-jun-2017, 15:20:28
    Author     : admin
--%>

<%@tag description="mensaje general" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${!empty mensaje}">
    <p class="<c:if test="${fn:contains(mensaje, '¡ERROR!')}">error</c:if>">${mensaje}</p>
</c:if>