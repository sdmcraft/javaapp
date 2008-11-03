<%-- 
    Document   : SyncAccountsPG
    Created on : Nov 3, 2008, 12:50:16 PM
    Author     : satyam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="utils.DBUtils" %>
<%@ page import="entity.BamaAccount" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sync Accounts</title>
    </head>
    <body>
        <%
            List<BamaAccount> accountList = DBUtils.getAccounts();%>
        <table border="1">
            <thead>
                <tr>
                    <th>Account ID</th>
                    <th>Account Name</th>
                    <th>Is Sync</th>                    
                </tr>
            </thead>
            <tbody>
                <%for (BamaAccount acct : accountList) {%>
                <tr>
                    <td><%= acct.getAccountId() %></td>
                    <td><%= acct.getName()%></td>                    
                    <td><%= acct.getInSync() %></td>
                    <td><input value="Sync Up" type="submit" <%= "Y".equals(acct.getInSync())? "disabled":""%> />
                </tr>
                <%}%>                
            </tbody>
        </table>
    </body>
</html>
