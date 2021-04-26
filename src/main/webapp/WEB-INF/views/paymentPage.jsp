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
<%@ taglib prefix="tag" uri="/WEB-INF/tlds/MyDateTag.tld" %>
<!DOCTYPE html>

<html>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="payment_page"/></title>
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

    <jsp:include page="common/nav_menu_user.jsp"></jsp:include>

    <div class="container-fluid">

        <div class="row">

            <div class="col-sm-2 empty-col">

            </div>

            <div class="col-sm-8 content-col" id="col2">
                <tag:MyDateTag/>


                <ul class="nav nav-pills">
                    <li class="active"><a data-toggle="pill" href="#tab1"><fmt:message key="last_payments"/></a></li>
                    <li><a data-toggle="pill" href="#tab2"><fmt:message key="payment_reports"/></a></li>
                </ul>

                <div class="tab-content">
                    <div id="tab1" class="tab-pane fade in active">
                        <form class="btn_form">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="card_number"/></th>
                                    <th scope="col"><fmt:message key="payment_date"/></th>
                                    <th scope="col"><fmt:message key="payment_type"/></th>
                                    <th scope="col"><fmt:message key="sum"/></th>
                                    <th scope="col"><fmt:message key="destination"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="n" items="${payments}">
                                    <tr>
                                        <td><c:out value="${n.cardNumber}"/></td>
                                        <td><c:out value="${n.paymentDate}"/></td>
                                        <td><c:out value="${n.idType}"/></td>
                                        <td><c:out value="${n.sum}"/></td>
                                        <td><c:out value="${n.destination}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <div id="tab2" class="tab-pane fade">
                        <form class="btn_form">
                            <div>
                                <h2><fmt:message key="payment_reports"/></h2>
                                <h4><fmt:message key="report_msg"/></h4>
                                <form>
                                </form>
                                <form action="${pageContext.request.contextPath}/DownloadServlet" method="post">
                                    <div>
                                        <input type="hidden" name="report" value="week"/>
                                        <input type="submit" name="submit" value="<fmt:message key="weekreport"/>"/>
                                    </div>
                                </form>
                                <form action="${pageContext.request.contextPath}/DownloadServlet" method="post">
                                    <div>
                                        <input type="hidden" name="report" value="month"/>
                                        <input type="submit" name="submit" value="<fmt:message key="monthreport"/>"/>
                                    </div>
                                </form>
                                <form action="${pageContext.request.contextPath}/DownloadServlet" method="post">
                                    <div>
                                        <input type="hidden" name="report" value="year"/>
                                        <input type="submit" name="submit" value="<fmt:message key="yearreport"/>"/>
                                    </div>
                                </form>
                            </div>
                        </form>
                    </div>
                </div>


            </div>

            <div class="col-sm-2 empty-col">

            </div>
        </div>
    </div>


    <jsp:include page="common/footer.jsp"></jsp:include>
    </body>
</fmt:bundle>
</html>
