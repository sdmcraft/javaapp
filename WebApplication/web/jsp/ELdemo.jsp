<%-- 
    Document   : ELdemo
    Created on : Nov 23, 2008, 1:27:54 PM
    Author     : sdmahesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        Expression1: ${"hi"}
        <% request.setAttribute("Fruit", "Apple"); %>
        <br>
        Expression2: ${requestScope.Fruit}
        <br>
        Expression3: ${param.name}
        <br>
        Expression4: ${1 < 2}
        <br>
        Expression5: ${not (1 < 2)}

    </body>
</html>
