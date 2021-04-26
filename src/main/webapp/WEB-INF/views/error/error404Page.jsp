<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.03.2021
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>

<% response.setStatus(404); %>
<html>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <head>
        <title><fmt:message key="error_404"/></title>

        <style>
            <%@include file="/WEB-INF/css/style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
    <jsp:include page="../common/header.jsp"></jsp:include>

    <div class="col-sm-2 empty-col">

    </div>

    <div class="col-sm-8 content-col">
        <button onclick="history.back()"><fmt:message key="back_prev"/></button>

        </br>

        <h3><fmt:message key="msg_404"/></h3>
    </div>

    <div class="col-sm-2 empty-col">

    </div>

    <jsp:include page="../common/footer.jsp"></jsp:include>

    </body>

</fmt:bundle>

</html>
