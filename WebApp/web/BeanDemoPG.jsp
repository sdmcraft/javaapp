<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="demoBean" scope="request" class="beans.DemoBean"></jsp:useBean>
<jsp:setProperty name="demoBean" property="name" value="Bean World!!" />
<h1>Hello <jsp:getProperty name="demoBean" property="name" /></h1>
Here's the EL: ${demoBean.name}
Here's another EL: ${demoBean.oddMethod()}
</body>
</html>