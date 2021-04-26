<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.04.2021
  Time: 14:19
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
        <title><fmt:message key="new_payment_page"/></title>
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

                <ul class="nav nav-pills">
                    <li class="active"><a data-toggle="pill" href="#tab1"><fmt:message key="new_payment"/></a></li>
                    <li><a data-toggle="pill" href="#tab2"><fmt:message key="new_refill"/></a></li>
                </ul>

                <div class="tab-content">
                    <div id="tab1" class="tab-pane fade in active">
                        <form class="btn_form" method="POST" action="Controller">

                            <input type="hidden" name="command" value="addnewpayment"/>
                            <fieldset>
                                <c:if test="${no_funds == 'NO_FUNDS'}">
                                <p>
                                    <span style="color: red; "><fmt:message key="no_funds_msg"/></span>
                                <p>
                                    </c:if>
                                    <!-- Form Name -->
                                    <legend><fmt:message key="enter_pay_data_msg"/></legend>

                                    <!-- Text input-->
                                <div class="form-group">
                                    <label class="control-label" for="outcomecard"><fmt:message
                                            key="from_card"/></label>
                                    <div>
                                        <input type="hidden" name="id_payment_type" value="-"/>
                                        <input type="hidden" name="id_account" value="${id_account}"/>
                                        <input type="hidden" name="id_card" value="${id_card}"/>
                                        <input id="outcomecard" name="OutcomeCard" type="text" value="${number_card}"
                                               class="form-control input-md" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="currentbalance"><fmt:message
                                            key="curr_balance"/></label>
                                    <div>
                                        <input id="currentbalance" name="CurrentBalance" type="text"
                                               value="${balance_card}" class="form-control input-md"
                                               readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="paymentvalue"><fmt:message key="pay_sum"/></label>
                                    <div>
                                        <input id="paymentvalue" name="PaymentValue" type="text"
                                               pattern="\d{2}+(\.\d{2})?" class="form-control input-md" required="">
                                    </div>
                                    <c:if test="${incorrect_payment == 'INCORRECT_PAYMENT'}">
                                    <p>
                                        <span style="color: red; "><fmt:message key="incorrect_payment_msg"/></span>
                                    <p>
                                        </c:if>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="destination"><fmt:message
                                            key="destination"/></label>
                                    <div>
                                        <input id="destination" name="Destination" type="text"
                                               class="form-control input-md">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div>
                                        <input type="submit" value="<fmt:message key="proceed_pay"/>"/>
                                    </div>
                                </div>

                            </fieldset>

                        </form>
                    </div>
                    <div id="tab2" class="tab-pane fade">
                        <form class="btn_form" method="POST" action="Controller">

                            <input type="hidden" name="command" value="addnewpayment"/>
                            <fieldset>

                                <legend><fmt:message key="enter_income_data_msg"/></legend>

                                <div class="form-group">
                                    <label class="control-label" for="incomecard"><fmt:message key="from_card"/></label>
                                    <div>
                                        <input type="hidden" name="id_payment_type" value="+"/>
                                        <input type="hidden" name="id_account" value="${id_account}"/>
                                        <input type="hidden" name="id_card" value="${id_card}"/>
                                        <input type="hidden" name="CurrentBalance" value="${balance_card}"/>
                                        <input type="hidden" name="Destination" value="income"/>
                                        <input id="incomecard" name="IncomeCard" type="text" value="${number_card}"
                                               class="form-control input-md" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="incomevalue"><fmt:message
                                            key="income_sum"/></label>
                                    <div>
                                        <input id="incomevalue" name="PaymentValue" type="text"
                                               class="form-control input-md" required="">
                                    </div>
                                </div>

                                <!-- Button -->
                                <div class="form-group">
                                    <div>
                                        <input type="submit" value="<fmt:message key="proceed_income"/>"/>
                                    </div>
                                </div>

                            </fieldset>

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
