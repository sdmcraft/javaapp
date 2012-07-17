<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
</head>

<body>
	<form id="uploadForm" name="uploadForm" action="/WebApp/ImageUpload"
		method="post" enctype="multipart/form-data">
		<input type="file" name="datafile" /> <input type="submit" />
	</form>
	<jsp:useBean id="dbBean" scope="request" class="beans.DBBean"></jsp:useBean>
	<table>
		<tr>
			<c:forEach var="imageId" items="${dbBean.imageList}"
				varStatus="status">
				<c:if test="${status.count%4 == 0} }">
		</tr>
		<tr>
			</c:if>
			<td><img width="25%" height="25%"
				src="/WebApp/ImageDisplay?image-id=${imageId}" /> </c:forEach></td>
		</tr>
	</table>
</body>

</html>