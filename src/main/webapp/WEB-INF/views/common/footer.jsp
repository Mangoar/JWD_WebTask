<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.03.2021
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<style>
    <%@include file="/WEB-INF/css/parts-css/footer.css" %>
</style>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <div class="footer-dark">
        <div class="container">
            <div class="col-sm-6 col-md-3 item">
                <h3><fmt:message key="about"/></h3>
                <ul>
                    <li><a href="Controller?command=gotoaboutpage"><fmt:message key="info"/></a></li>
                    <li><a href="Controller?command=gotocontactspage"><fmt:message key="contact"/></a></li>
                </ul>
            </div>
            <div class="col-sm-6 col-md-3 item">
                <div class="col item social">
                    <a href="https://www.facebook.com/mangoar"><i class="fa fa-facebook"></i></a>
                    <a href="javascript:void(0);" onclick="alert('Skype: arteman1996');"><i class="fa fa-skype"></i></a>
                    <a href="https://www.linkedin.com/in/artsiom-homan-a13040174/"><i class="fa fa-linkedin"></i></a>
                </div>
            </div>
            <div class="col-md-6 item text">
                <h3>WebAppPayments</h3>
                <p><fmt:message key="welcome_msg"/></p>
            </div>
        </div>
    </div>

</fmt:bundle>