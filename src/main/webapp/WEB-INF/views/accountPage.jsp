<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.03.2021
  Time: 1:45
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
        <title><fmt:message key="account_page"/></title>
        <style>
            <%@include file="/WEB-INF/css/style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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


                <c:choose>
                    <c:when test="${id_user_role == '1'}">
                        <form class="btn_form">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                        <%--                                <th scope="col">Id</th>--%>
                                    <th scope="col"><fmt:message key="acc_number"/></th>
                                    <th scope="col"><fmt:message key="balance"/></th>
                                        <%--                                <th scope="col">Id_currency</th>--%>
                                    <th scope="col"><fmt:message key="delete"/></th>
                                    <th scope="col"><fmt:message key="cards"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="n" items="${accounts}">
                                    <tr>
                                        <form action="Controller" method="post">
                                                <%--                                        <td><c:out value="${n.id}"/></td>--%>
                                            <td><c:out value="${n.accNumber}"/></td>
                                            <td><c:out value="${n.balance}"/></td>
                                                <%--                                        <td><c:out value="${n.id_currency}"/></td>--%>
                                            <td>
                                                <input type="hidden" name="command" value="deleteaccount"/>
                                                <input type="hidden" name="idaccount" value="${n.id}"/>
                                                <input type="submit" name="submit" value="<fmt:message key="delete"/>"/>
                                            </td>
                                        </form>
                                        <form action="Controller" method="post">
                                            <td>
                                                <input type="hidden" name="command" value="getcards"/>
                                                <input type="hidden" name="balance" value="${n.balance}"/>
                                                <input type="hidden" name="idaccount" value="${n.id}"/>
                                                <input type="submit" name="submit"
                                                       value="<fmt:message key="choose_cards"/>"/>
                                            </td>
                                        </form>
                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </c:when>


                    <c:when test="${id_user_role == '2'}">
                        <ul class="nav nav-pills">
                            <li class="active"><a data-toggle="pill" href="#tab1"><fmt:message key="my_accounts"/></a>
                            </li>
                            <li><a data-toggle="pill" href="#tab2"><fmt:message key="create_account"/></a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="tab1" class="tab-pane fade in active">
                                <form class="btn_form">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                                <%--                                        <th scope="col">Id</th>--%>
                                            <th scope="col"><fmt:message key="acc_number"/></th>
                                            <th scope="col"><fmt:message key="balance"/></th>
                                                <%--                                        <th scope="col">Id_currency</th>--%>
                                            <th scope="col"><fmt:message key="cards"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="n" items="${accounts}">
                                            <form action="Controller" method="post">
                                                <tr>
                                                        <%--                                                <td><c:out value="${n.id}"/></td>--%>
                                                    <td><c:out value="${n.accNumber}"/></td>
                                                    <td><c:out value="${n.balance}"/></td>
                                                        <%--                                                <td><c:out value="${n.id_currency}"/></td>--%>
                                                    <td>
                                                        <input type="hidden" name="command" value="getcards"/>
                                                        <input type="hidden" name="balance" value="${n.balance}"/>
                                                        <input type="hidden" name="idaccount" value="${n.id}"/>
                                                        <input type="submit" name="submit"
                                                               value="<fmt:message key="choose_cards"/>"/>
                                                    </td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                            <div id="tab2" class="tab-pane fade">
                                <form class="btn_form" action="Controller" method="post">
                                    <h2><fmt:message key="create_account"/></h2>
                                    <h4><fmt:message key="create_acc_msg"/></h4>
                                        ${n.id_user}

                                    <div class="form-group">
                                        <label class="control-label" for="textinput"><fmt:message
                                                key="currency"/></label>
                                        <div>
                                            <select id="textinput" name="IdCurrency">
                                                <option value="1">BYN</option>
                                                <option value="2">USD</option>
                                                <option value="3">EUR</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div>
                                        <input type="hidden" name="command" value="addnewaccount"/>
                                        <input type="submit" name="submit" value="<fmt:message key="create_account"/>"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:when>
                </c:choose>


            </div>

            <div class="col-sm-2 empty-col">

            </div>
        </div>
    </div>


    <jsp:include page="common/footer.jsp"></jsp:include>
    </body>
</fmt:bundle>
</html>
