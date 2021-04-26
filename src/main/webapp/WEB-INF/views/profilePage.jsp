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
        <title><fmt:message key="profile_page"/></title>
        <style>
            <%@include file="/WEB-INF/css/style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body>
    <jsp:include page="common/header.jsp"></jsp:include>


    <c:choose>
        <c:when test="${id_user_role == '1'}">
            <jsp:include page="common/nav_menu_admin.jsp"></jsp:include>
        </c:when>
        <c:when test="${id_user_role == '2'}">
            <jsp:include page="common/nav_menu_user.jsp"></jsp:include>
        </c:when>
    </c:choose>
    <div class="container-fluid">

        <div class="row">

            <div class="col-sm-2 empty-col">

            </div>

            <div class="col-sm-8 content-col" id="col2">

                <form class="btn_form" action="${pageContext.request.contextPath}/FileUploadingServlet" method="post"
                      enctype="multipart/form-data">
                    <fieldset>
                        <legend><fmt:message key="user_photo"/></legend>
                        <img src="${user.photoPath}" width="100" height="100" alt="Cannot display">
                        <input type="file" name="file">
                        <input type="submit" value="<fmt:message key="update_photo_btn"/>"/>
                    </fieldset>
                </form>

                <form class="btn_form">
                    <fieldset>
                        <legend><fmt:message key="user_info"/></legend>

                        <form method="POST" action="Controller">
                            <input type="hidden" name="command" value="updateuserinfo"/>
                            <div class="form-group">
                                <label class="control-label" for="passwordinput"><fmt:message key="password"/></label>
                                <div>
                                    <input id="passwordinput" name="NewUserPassword" type="text"
                                           value="${user.password}" class="form-control input-md" required=""
                                           value="${user.password}">
                                    <c:if test="${message_password == 'INCORRECT_PASSWORD'}">
                                    <p>
                                        <span style="color: red; "><fmt:message key="invalid_password"/></span>
                                    <p>
                                        </c:if>
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="control-label" for="fullnameinput"><fmt:message key="fullname"/></label>
                                <div>
                                    <input id="fullnameinput" name="NewUserFullname" type="text"
                                           value="${user.fullname}" class="form-control input-md" required=""
                                           value="${user.fullname}">
                                    <c:if test="${message_fullname == 'INCORRECT_FULLNAME'}">
                                    <p>
                                        <span style="color: red; "><fmt:message key="invalid_fio"/></span>
                                    <p>
                                        </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="userpassportinput"><fmt:message
                                        key="passport"/></label>
                                <div>
                                    <input id="userpassportinput" name="NewUserPassport" type="text"
                                           value="${user.passport}" class="form-control input-md" required=""
                                           value="${user.passport}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="useremailinput"><fmt:message key="mail"/></label>
                                <div>
                                    <input id="useremailinput" name="NewUserEmail" type="text" value="${user.email}"
                                           class="form-control input-md" required="" value="${user.email}">
                                    <c:if test="${message_email == 'INCORRECT_EMAIL'}">
                                    <p>
                                        <span style="color: red; "><fmt:message key="invalid_mail"/></span>
                                    <p>
                                        </c:if>
                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <div>
                                    <input type="submit" value="<fmt:message key="update_info_btn"/>"/>
                                </div>
                            </div>

                        </form>
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