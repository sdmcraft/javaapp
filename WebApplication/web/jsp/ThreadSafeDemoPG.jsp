<%-- 
    Document   : ThreadSafeDemoPG
    Created on : Nov 4, 2008, 10:50:22 AM
    Author     : satyam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isThreadSafe="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <h2>Hello </h2>
        <% try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }%>
        <h2>World!</h2>
    </body>
</html>
