<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.03.2021
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple Web Application</title>
</head>

<body>


<jsp:forward page="Controller">
    <jsp:param name="command" value="gotomainpage"/>
</jsp:forward>

</body>
</html>
