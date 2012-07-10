<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>

<head>
</head>

<body>
	<form id="uploadForm" name="uploadForm" action="/WebApp/ImageUpload"
		method="post" enctype="multipart/form-data">
		<input type="file" name="datafile" /> <input type="submit" />
	</form>
	<jsp:useBean id="dbBean" scope="request" class="beans.DBBean"></jsp:useBean>
	<c:forEach var="imageId" items="${dbBean.ImageList}">
		<h1>${imageId}</h1>
	</c:forEach>
</body>

</html>