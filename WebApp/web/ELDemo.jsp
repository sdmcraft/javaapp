<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	synchronized (pageContext) {
		Class thisClass = getClass();
		pageContext.setAttribute("thisClass", thisClass,
				PageContext.PAGE_SCOPE);
		System.out.println("Stored reference");

		Class theClass = (Class) pageContext.getAttribute("thisClass",
				PageContext.PAGE_SCOPE);
		System.out.println("The retrieved reference is " + theClass);
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
The class that instantiated this JSP is
<c:out value="${pageScope.thisClass.name}" />
.

</body>
</html>