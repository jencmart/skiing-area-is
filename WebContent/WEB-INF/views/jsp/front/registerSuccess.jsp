<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPageFront viewname="index">

    <jsp:attribute name="frontbody">
    <!-- Jumbotron Header -->
    <header class="jumbotron my-4">
        <h1 class="display-3">Dekujeme za registraci ${name} !</h1>
        <p class="lead"> Nyni se muzes prihlasit zde :
        </p>
        <p class="lead">
            <a href="/login" class="btn btn-success btn-lg">Prihlasit se!</a>
        </p>

    </header>

    <!-- Page Features -->
    <div class="row text-center">


    </div>
    <!-- /.row -->

    </jsp:attribute>

</t:genericPageFront>