<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag description="Management Page template" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="title" required="true" %>
<%@attribute name="viewname" required="true" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="filter" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="modals" fragment="true" %>


<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
-->
    <!-- Bootstrap 3 CSS  second try  -->

    <spring:url value="/resources/basic/css/bootstrap.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet">


    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Page Specific CSS -->
    <link href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css" rel="stylesheet">


    <!-- management CSS -->
    <spring:url value="/resources/basic/css/management.css" var="coreCss"/>
    <link href="${coreCss}" rel="stylesheet"/>


    <title> ${title} </title>
</head>
<body>


<div id="wrapper">

    <!------------------ Sidebar --------------------------->
    <nav class="navbar navbar-inverse navbar-fixed-top  navbar-dark bg-dark" role="navigation">

        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/management/"> Ski areál | Správa</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!-- nav links for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">

            <ul class="nav navbar-nav side-nav">
                <li class="    <c:if test="${viewname=='index'}">active</c:if>     "><a
                        href="${pageContext.request.contextPath}/management"> Prehled</a></li>
                <li class="    <c:if test="${viewname=='order'}">active</c:if>     "><a href="/management/order">
                    Objednavky</a></li>
                <li class="    <c:if test="${viewname=='chipcard'}">active</c:if>  "><a href="/management/chipcard">
                    Karty</a></li>
                <li class="    <c:if test="${viewname=='skipass'}">active</c:if>   "><a href="/management/skipass">
                    Skipasy </a></li>
                <li class="    <c:if test="${viewname=='customer'}">active</c:if>  "><a href="/management/customer">
                    Zakaznici</a></li>
                <li class="    <c:if test="${viewname=='employee'}">active</c:if>  "><a href="/management/employee">
                    Zamestnanci </a></li>
            </ul>
            <!--------- TOP NAVBAR  pouze ikonky ----------->

            <ul class="nav navbar-nav navbar-right navbar-user">
                <li class="dropdown user-dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="fa fa-user"></i> ${loggedUsername} ( ${loggedName} ${loggedSurname} ) <b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="fa fa-user"></i> Profil </a></li>
                        <li class="divider"></li>

                        <li>
                            <form action="/logout" method="post">
                                <button type="submit"><i class="fa fa-power-off"></i> Odhlasit se</button>
                                <sec:csrfInput/>
                            </form>

                        </li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->


    </nav>


    <!----------------- PAGE CONTENT ------------------------------------------------------------>
    <div id="page-wrapper">

        <div class="row">

            <jsp:invoke fragment="header"/>

        </div><!-- /.row -->

        <div class="row">

            <jsp:invoke fragment="filter"/>

        </div>

        <!--falsh alerts todo !!!!!! -->


        <div class="row">

            <jsp:invoke fragment="content"/>

        </div>

    </div>

</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>


<!-- front JS
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

 -->
<spring:url value="/resources/basic/js/bootstrap.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>


<spring:url value="/resources/basic/js/management.js" var="managementJs"/>
<script src="${managementJs}"></script>


</body>
</html>