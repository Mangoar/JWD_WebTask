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

<jsp:useBean id="now" class="java.util.Date"/>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="cards_page"/></title>
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
                <fmt:formatDate var="compare_date" pattern = "yyyy-MM-dd"
                                value = "${now}" />
            </div>

            <div class="col-sm-8 content-col" id="col2">
                <c:if test="${id_user_role == '2'}">
                    <ul class="nav nav-pills">
                        <li class="active"><a data-toggle="pill" href="#tab1"><fmt:message key="my_cards"/></a></li>
                        <li><a data-toggle="pill" href="#tab2"><fmt:message key="create_new_card"/></a></li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab1" class="tab-pane fade in active">
                            <form class="btn_form">
                                <h4><fmt:message key="cards_msg"/></h4>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th scope="col"><fmt:message key="card_number"/></th>
                                        <th scope="col">CVV</th>
                                        <th scope="col"><fmt:message key="valid_date"/></th>
                                        <th scope="col"><fmt:message key="payment"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="n" items="${cards}">
                                        <form action="Controller" method="post">
                                            <tr>
                                                <td><c:out value="${n.number}"/></td>
                                                <td><c:out value="${n.cvvCode}"/></td>
                                                <td><c:out value="${n.validDate}"/></td>
                                                <td>
                                                    <input type="hidden" name="command" value="gotonewpaymentpage"/>
                                                    <input type="hidden" name="idaccount" value="${id_account}"/>
                                                    <input type="hidden" name="validdate" value="${n.validDate}"/>
                                                    <input type="hidden" name="balancecard" value="${balance}"/>
                                                    <input type="hidden" name="idcard" value="${n.id}"/>
                                                    <input type="hidden" name="numbercard" value="${n.number}"/>
                                                    <c:if test="${n.validDate lt compare_date}">
                                                    <input type="submit" name="submit" style="background-color: red"
                                                           value="<fmt:message key="new_payment"/>"/>
                                                    </c:if>
                                                    <c:if test="${n.validDate ge compare_date}">
                                                        <input type="submit" name="submit"
                                                               value="<fmt:message key="new_payment"/>"/>
                                                    </c:if>
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
                                <h2><fmt:message key="create_new_card"/></h2>
                                <h4><fmt:message key="create_card_msg"/></h4>
                                <div>
                                    <input type="hidden" name="command" value="addnewcard"/>
                                    <input type="hidden" name="balance" value="${balance}"/>
                                    <input type="hidden" name="idaccount" value="${id_account}"/>
                                    <input type="submit" name="submit" value="<fmt:message key="create_new_card"/>"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:if>


                <c:if test="${id_user_role == '1'}">
                    <form class="btn_form">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                    <%--                            <th scope="col">Account ID</th>--%>
                                <th scope="col"><fmt:message key="card_number"/></th>
                                <th scope="col"><fmt:message key="valid_date"/></th>
                                <th scope="col"><fmt:message key="extend_date"/></th>
                                <th scope="col"><fmt:message key="save"/></th>
                                <th scope="col"><fmt:message key="delete"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="n" items="${cards}">

                                <tr>
                                    <form action="Controller" method="post">
                                            <%--                                <td><c:out value="${n.id_account}"/></td>--%>
                                        <td><c:out value="${n.number}"/></td>
                                        <td><c:out value="${n.validDate}"/></td>
                                        <td>
                                            <input type="date" name="new_valid_date" value=""/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="command" value="extendcardvaliddate"/>
                                            <input type="hidden" name="idcard" value="${n.id}"/>
                                            <input type="submit" name="submit"
                                                   value="<fmt:message key="save_card_date"/>"/>
                                        </td>
                                    </form>
                                    <form action="Controller" method="post">
                                        <td>
                                            <input type="hidden" name="command" value="deletecard"/>
                                            <input type="hidden" name="idcard" value="${n.id}"/>
                                            <input type="submit" name="submit"
                                                   value="<fmt:message key="delete_card"/>"/>
                                        </td>
                                    </form>
                                </tr>

                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </c:if>


            </div>

            <div class="col-sm-2 empty-col">

            </div>
        </div>
    </div>


    <jsp:include page="common/footer.jsp"></jsp:include>
    </body>
</fmt:bundle>
</html>
