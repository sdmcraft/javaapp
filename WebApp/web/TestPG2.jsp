<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.Map" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Map<String, String[]> paramMap = request.getParameterMap();
%>
<table>
	<thead>
		<tr>
			<td>Param Name</td>
			<td>Param Value</td>
		</tr>
	</thead>
	<tbody>

		<%
			for (String key : paramMap.keySet()) {
		%>
		<tr>
			<td><%=key%></td>
			<td><%=((String[])paramMap.get(key))[0]%></td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>
</body>
</html>