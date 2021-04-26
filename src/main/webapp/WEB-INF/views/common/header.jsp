<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.03.2021
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<style><%@include file="/WEB-INF/css/parts-css/header.css"%></style>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

<div class="header">
    <a href="Controller?command=gotomainpage" class="logo">WebAppPayments</a>
    <div class="header-right">
        <a href="Controller?command=gotomainpage"><fmt:message key="home"/></a>
        <a href="Controller?command=gotocontactspage"><fmt:message key="contact"/></a>
        <a href="Controller?command=gotoaboutpage"><fmt:message key="about"/></a>
        <c:if test="${id_user_role == null}">
        <a href="Controller?command=gotologinpage"><fmt:message key="log_reg"/></a>
        </c:if>
        <c:if test="${id_user_role == '1'}">
            <a href="Controller?command=logout"><fmt:message key="logout"/></a>
        </c:if>
        <c:if test="${id_user_role == '2'}">
            <a href="Controller?command=logout"><fmt:message key="logout"/></a>
        </c:if>
    </div>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="chooselocalecommand" />
        <input type="hidden" name="url" value="${pageContext.findAttribute("url")}">
        <input type="submit" value="ENG \ РУС"/>
    </form>
</div>

</fmt:bundle>