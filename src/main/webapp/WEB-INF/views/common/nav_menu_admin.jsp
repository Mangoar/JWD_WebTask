<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.04.2021
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="text">

    <nav class="navbar navbar-expand-lg" style="margin-bottom: 0;">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">

                <a class="nav-item nav-link" href="Controller?command=getprofileinfo"
                   onmouseover="this.style.color='orange'; this.style.backgroundColor='#736AFF';" onmouseout="this.style.color=''; this.style.backgroundColor='';"
                   style="font-size: medium; height: 100%; margin-right: 15px;"><fmt:message key="my_profile"/></a>

                <a class="nav-item nav-link" href="Controller?command=getusers"
                   onmouseover="this.style.color='orange'; this.style.backgroundColor='#736AFF';" onmouseout="this.style.color=''; this.style.backgroundColor='';"
                   style="font-size: medium; height: 100%; margin-right: 15px;"><fmt:message key="users"/></a>

            </div>
        </div>
    </nav>
</fmt:bundle>
