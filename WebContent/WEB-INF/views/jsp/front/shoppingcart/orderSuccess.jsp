<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericPageFront viewname="shoppingcart">

    <jsp:attribute name="frontbody">
    <!-- Jumbotron Header -->
    <header class="jumbotron my-4">
        <h1 class="display-3">Objednavka c. ${order.idOrder} uspesne vytvorena. </h1>
        Na pokladne toto cislo nahlaste spolu se svym jmenem.
        Dekujeme za nakup a prejeme hezky den.
    </header>

    <!-- Page Features -->
    <!-- /.row -->

    </jsp:attribute>

</t:genericPageFront>