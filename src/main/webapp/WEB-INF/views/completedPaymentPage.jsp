<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.03.2021
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="completed_payment_page"/></title>
        <style>
            <%@include file="/WEB-INF/css/style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body>
    <jsp:include page="common/header.jsp"></jsp:include>


    <jsp:include page="common/nav_menu_user.jsp"></jsp:include>

    <div class="container-fluid">

        <div class="row">

            <div class="col-sm-2 empty-col">

            </div>

            <div class="col-sm-8 content-col" id="col2">
                <form class="btn_form">

                    <h2><fmt:message key="payment_head_msg"/></h2>
                    <h4><fmt:message key="payment_msg"/></h4>
                </form>
                <form class="btn_form" action="Controller" method="post">
                    <div>
                        <form action="Controller" method="post">
                            <div>
                                <input type="hidden" name="command" value="getpayments"/>
                                <input type="submit" name="submit" value="<fmt:message key="go_payments_page"/>"/>
                            </div>
                        </form>
                        <form action="${pageContext.request.contextPath}/DownloadServlet" method="post">
                            <div>
                                <input type="hidden" name="report" value="now"/>
                                <input type="submit" name="submit" value="<fmt:message key="download_check"/>"/>
                            </div>
                        </form>
                    </div>
                </form>

            </div>

            <div class="col-sm-2 empty-col">

            </div>
        </div>
    </div>


    <jsp:include page="common/footer.jsp"></jsp:include>
    </body>
</fmt:bundle>
</html>
