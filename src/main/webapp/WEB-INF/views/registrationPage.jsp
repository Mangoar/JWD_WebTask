<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.03.2021
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi">

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <head>
        <title><fmt:message key="register_page"/></title>
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

            <div class="col-sm-8 content-col">

                <form class="btn_form" method="post" action="Controller">

                    <input type="hidden" name="command" value="registernewuser"/>
                    <fieldset>
                        <!-- Form Name -->
                        <legend><fmt:message key="enter_credentials"/></legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="control-label" for="textinput"><fmt:message key="login"/></label>
                            <div>
                                <input id="textinput" name="UserLogin" type="text"
                                       class="form-control input-md">
                                <c:if test="${message_login == 'INCORRECT_LOGIN'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="invalid_login"/></span>
                                <p>
                                    </c:if>
                                    <c:if test="${message_used_login == 'USED_LOGIN'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="used_login"/></span>
                                <p>
                                    </c:if>
                            </div>
                        </div>

                        <!-- Password input-->
                        <div class="form-group">
                            <label class="control-label" for="passwordinput"><fmt:message key="password"/></label>
                            <div>
                                <input id="passwordinput" name="UserPassword" type="password"
                                       class="form-control input-md">
                                <c:if test="${message_password == 'INCORRECT_PASSWORD'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="invalid_password"/></span>
                                <p>
                                    </c:if>
                            </div>
                        </div>

                        <div class="form-group ">
                            <label class="control-label" for="textinput"><fmt:message key="fullname"/></label>
                            <div>
                                <input name="UserFullname" type="text"
                                       class="form-control input-md">
                                <c:if test="${message_fullname == 'INCORRECT_FULLNAME'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="invalid_fio"/></span>
                                <p>
                                    </c:if>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="control-label" for="textinput"><fmt:message key="birthdate"/></label>
                            <div>
                                <input name="UserBirthdate" type="date"
                                       class="form-control input-md">
                                <c:if test="${message_birthdate == 'INCORRECT_BIRTHDATE'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="invalid_birthdate"/></span>
                                <p>
                                    </c:if>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="control-label" for="textinput"><fmt:message key="passport"/></label>
                            <div>
                                <input name="UserPassport" type="text"
                                       class="form-control input-md">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="textinput"><fmt:message key="mail"/></label>
                            <div>
                                <input name="UserEmail" type="text" class="form-control input-md">
                                <c:if test="${message_email == 'INCORRECT_EMAIL'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="invalid_mail"/></span>
                                <p>
                                    </c:if>
                                    <c:if test="${message_used_mail == 'USED_MAIL'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="used_mail"/></span>
                                <p>
                                    </c:if>
                            </div>

                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <div>
                                <input type="submit" value="<fmt:message key="register"/>"/>
                            </div>
                        </div>

                        <a href="Controller?command=gotologinpage"><fmt:message key="login_btn"/></a>
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
