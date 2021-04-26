<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.04.2021
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <head>
        <title><fmt:message key="contacts_page"/></title>

        <style>
            <%@include file="/WEB-INF/css/style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>

    <body>
    <jsp:include page="../common/header.jsp"></jsp:include>

    <c:choose>
        <c:when test="${id_user_role == '1'}">
            <jsp:include page="../common/nav_menu_admin.jsp"></jsp:include>
        </c:when>
        <c:when test="${id_user_role == '2'}">
            <jsp:include page="../common/nav_menu_user.jsp"></jsp:include>
        </c:when>
    </c:choose>

    <div class="container-fluid">

        <div class="row">

            <div class="col-sm-2 empty-col">

            </div>

            <div class="col-sm-8 content-col">

                <h2><fmt:message key="dear_users"/></h2>
                <h5><fmt:message key="dear_users_msg"/></h5>

                <form class="btn_form" method="POST" action="SendEmail">

                    <input type="hidden" name="command" value="#"/>
                    <fieldset>
                        <legend><fmt:message key="contact_to_do"/></legend>

                        <c:if test="${message_send == 'MESSAGE_SEND'}">
                        <p>
                            <span style="color: green; "><fmt:message key="msg_send"/></span>
                        <p>
                            </c:if>
                            <c:if test="${message_email == 'INCORRECT_EMAIL'}">
                        <p>
                            <span style="color: red; "><fmt:message key="invalid_mail"/></span>
                        <p>
                            </c:if>
                        <div class="form-group">
                            <label class="control-label" for="textusername"><fmt:message key="your_name"/></label>
                            <div>
                                <input id="textusername" name="UserName" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="textmail"><fmt:message key="your_mail"/></label>
                            <div>
                                <input id="textmail" name="UserEmail" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="textsubject"><fmt:message key="your_subject"/></label>
                            <div>
                                <input id="textsubject" name="MailSubject" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="textmessage"><fmt:message key="your_text"/></label>
                            <div>
                                <textarea id="textmessage" name="MailMessage" class="form-control input-md"></textarea>
                            </div>
                        </div>


                        <!-- Button -->
                        <div class="form-group">
                            <div>
                                <input type="submit" value="<fmt:message key="btn_send"/>"/>
                            </div>
                        </div>


                    </fieldset>

                </form>

            </div>

            <div class="col-sm-2 empty-col">

            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp"></jsp:include>
    </body>
</fmt:bundle>
</html>
