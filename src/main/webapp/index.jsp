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
                    url:"/test/helloworld",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"msg":"sayHelloWorld"}',
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
</body>
</html>
