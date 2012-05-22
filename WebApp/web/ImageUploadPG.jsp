<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    
    <head>
    </head>
    
    <body>
        <form id="uploadForm" name="uploadForm" action="/WebApp/ImageUpload"
        method="post" enctype="multipart/form-data">
            <input type="file" name="datafile" />            
            <input type="submit" />
        </form>
    </body>

</html>