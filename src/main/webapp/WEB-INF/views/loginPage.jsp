<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.03.2021
  Time: 18:15
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
        <title><fmt:message key="login_page"/></title>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
        <style>
            <%@include file="/WEB-INF/css/style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body>
    <jsp:include page="common/header.jsp"></jsp:include>

    <div class="container-fluid">

        <div class="row">

            <div class="col-sm-2 empty-col">

            </div>

            <div class="col-sm-8 content-col" id="col2">
                <form class="btn_form" method="POST" action="Controller">
                    <c:if test="${message == 'REGISTER_SUCCESS'}">
                    <p>
                        <span style="color: green; "><fmt:message key="good_register"/></span>
                    <p>
                        </c:if>
                        <input type="hidden" name="command" value="login"/>
                    <fieldset>
                        <!-- Form Name -->
                        <legend><fmt:message key="login_enter_credentials"/></legend>

                        <c:if test="${message_fail_log_in == 'FAIL_LOG_IN'}">
                        <p>
                            <span style="color: red; "><fmt:message key="fail_login1"/></span>
                            <span style="color: red; "><fmt:message key="fail_login2"/></span>
                        <p>
                            </c:if>
                        <div class="form-group">
                            <label class="control-label" for="textinput"><fmt:message key="login"/></label>
                            <div>
                                <input id="textinput" name="UserLogin" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Password input-->
                        <div class="form-group">
                            <label class="control-label" for="passwordinput"><fmt:message key="password"/></label>
                            <div>
                                <input id="passwordinput" name="UserPassword" type="password"
                                       class="form-control input-md">
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <div>
                                <input type="submit" value="<fmt:message key="login_btn"/>"/>
                            </div>
                        </div>

                        <a href="Controller?command=gotoregisterpage"><fmt:message key="not_reg_yet"/></a>

                    </fieldset>

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