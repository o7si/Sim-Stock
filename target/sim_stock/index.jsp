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
        $(function () {
            $("#test").click(function () {
                $.ajax({
                    // url:"test/helloworld",
                    url: "user/information/show",
                    contentType:"application/json;charset=UTF-8",
                    // data:'{"msg":"sayHelloWorld"}',
                    data: '{"id":1}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        alert(data.msg);
                    },
                    error:function () {
                        alert("No");
                    }
                });
            });
        });
    </script>
    <title>Hello World</title>
</head>
<body>
<h3>Hello World</h3>
<button id="test">sayHelloWorld</button>
<h3>SpringMVC文件上传</h3>
<form action="user/information/upload/avatar" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>
<form action="user/information/show" method="post">
    <input type="submit" value="123">
</form>
</body>
</html>
