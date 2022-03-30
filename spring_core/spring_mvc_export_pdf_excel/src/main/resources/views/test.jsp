<%--
  Created by IntelliJ IDEA.
  User: lzj
  Date: 2021/12/16
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
userName1属性的值为：
<c:out value="username!"
       default="unknown" />
userName2属性的值为：
<c:out value="username2">
unknown
</c:out>
</body>
</html>
