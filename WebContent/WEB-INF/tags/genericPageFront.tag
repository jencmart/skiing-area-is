<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag description="Overall Front page" pageEncoding="ISO-8859-2" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="viewname" required="true" %>

<%@attribute name="frontbody" fragment="true" %>


<!DOCTYPE html>
<html lang="cz">

<head>
    <meta charset="charset=ISO-8859-2">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="SI1-SLA">

    <title>SYSTEM LYZARSKEHO AREALU </title>


    <!-- Bootstrap CSS  -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css"
          integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">

    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"
            integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+"
            crossorigin="anonymous"></script>
    <!-- front CSS -->
    <spring:url value="/resources/basic/css/front.css" var="coreCss"/>
    <link href="${coreCss}" rel="stylesheet"/>

    <spring:url value="/resources/basic/css/frontLogin.css" var="loginCss"/>
    <link href="${loginCss}" rel="stylesheet">


</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">Ski areál FIT-ÈVUT</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item  <c:if test="${viewname=='index'}"> active</c:if>">
                    <a class="nav-link" href="/">Úvodní strana
                        <c:if test="${viewname=='index'}">
                            <span class="sr-only">(current)</span>
                        </c:if>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link  <c:if test="${viewname=='products'}"> active</c:if>"
                       href="/products">Produkty</a>
                    <c:if test="${viewname=='products'}">
                        <span class="sr-only">(current)</span>
                    </c:if>
                </li>


                <sec:authorize access="authenticated" var="authenticated"/>
                <c:choose>
                    <c:when test="${authenticated}">

                        <c:choose>
                            <c:when test="${role=='ROLE_ADMIN' }">
                                <li class="nav-item">
                                    <a class="btn btn-warning btn-sm" href="/management">Administrace</a>
                                </li>
                            </c:when>

                            <c:otherwise>
                                <li class="nav-item  <c:if test="${viewname=='profile'}"> active</c:if>">
                                    <a class="nav-link" href="/profile"> <i class=" fa fa-user "    <c:if
                                            test="${viewname!='profile'}"> style="color:grey" </c:if> <c:if
                                            test="${viewname=='profile'}"> style="color:white" </c:if>></i>
                                        Profil</a>
                                </li>

                            </c:otherwise>

                        </c:choose>

                        <li class="nav-item  <c:if test="${viewname=='login'}"> active</c:if>">
                            <form action="/logout" method="post">
                                <button class="btn btn-danger btn-sm" type="submit"><i class="fa fa-power-off"></i>
                                    Odhlasit se
                                </button>
                                <sec:csrfInput/>
                            </form>
                        </li>

                    </c:when>
                    <c:otherwise>
                        <li class="nav-item  <c:if test="${viewname=='profile'}"> active</c:if>">
                            <a class="nav-link" href="/profile"> <i class=" fa fa-user "    <c:if
                                    test="${viewname!='profile'}"> style="color:grey" </c:if> <c:if
                                    test="${viewname=='profile'}"> style="color:white" </c:if>></i>Profil</a>
                        </li>
                    </c:otherwise>
                </c:choose>


                <li class="nav-item  <c:if test="${viewname=='shoppingcart'}"> active</c:if> ">
                    <a class="nav-link" href="/shoppingcart">
                        <span class="fa-layers fa-fw fa-lg">
                        <i class="fas fa-shopping-basket "  <c:if test="${viewname!='cart'}"> style="color:grey" </c:if>     <c:if
                                test="${viewname=='cart'}"> style="color:white" </c:if>></i>
                        <span id="shoppingCartCount" class="fa-layers-counter fa-2x"
                              style="background:Tomato"> ${shoppingCartCount} </span>
                        </span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container fillz" id="wrap">
    <div id="main" class="container clear-top">
        <jsp:invoke fragment="frontbody"/>
    </div>
</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark footer">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; SLA 2018</p>
    </div>
    <!-- /.container -->


</footer>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>


<!-- front JS -->
<spring:url value="/resources/basic/js/front.js" var="frontJS"/>
<script src="${frontJS}"></script>
</body>

</html>