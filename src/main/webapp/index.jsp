<%--
  Created by IntelliJ IDEA.
  User: ShaYuan
  Date: 2019/6/3
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script>
    </script>
    <title>Hello World</title>
</head>
<body>
<h3>Hello World</h3>
<h3>SpringMVC文件上传</h3>
<form action="user/information/upload/avatar" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
