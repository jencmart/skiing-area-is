<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:genericPageFront viewname="index">

    <jsp:attribute name="frontbody">

        <div class="jumbotron my-4  col-centered">


            <h1 class="display-4">Registrace uzivatele</h1>
            <hr class="my-4">

            <spring:url value="/registration" var="checkoutConfirmAction"/>


    <form:form class="form-horizontal" method="post" modelAttribute="customerUserFrom"
               action="${checkoutConfirmAction}">

    <%-- Name --%>
    <c:set var="from1" value="name"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Jmeno</label>
                    <div class="col-sm-10">
                <form:input path="${from1}" type="text" class="form-control"
                            id="${from1}" placeholder="Jmeno"/>
                        <form:errors path="${from1}" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

    <%-- Surname --%>
    <c:set var="from1" value="surname"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Prijmeni</label>
                    <div class="col-sm-10">
                <form:input path="${from1}" type="text" class="form-control"
                            id="${from1}" placeholder="Prijmeni"/>
                        <form:errors path="${from1}" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

    <%-- Password --%>
    <c:set var="from1" value="password"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Heslo</label>
                    <div class="col-sm-10">
                <form:input path="${from1}" type="password" class="form-control"
                            id="${from1}" placeholder="Heslo"/>
                        <form:errors path="${from1}" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

    <%-- Password confirmation --%>
    <c:set var="from1" value="passwordConfirmation"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Potvrzeni hesla</label>
                    <div class="col-sm-10">
                <form:input path="${from1}" type="password" class="form-control"
                            id="${from1}" placeholder="Heslo znovu"/>
                        <form:errors path="${from1}" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

    <%-- Email --%>
    <c:set var="from1" value="email"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                <form:input path="${from1}" type="text" class="form-control"
                            id="${from1}" placeholder="Email"/>
                        <form:errors path="${from1}" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

    <%-- Phone --%>
    <c:set var="from1" value="phone"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Telefon</label>
                    <div class="col-sm-10">
                <form:input path="${from1}" type="text" class="form-control"
                            id="${from1}" placeholder="Telefon"/>
                        <form:errors path="${from1}" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

        <sec:csrfInput/>

        <%--ACTION BUTTONS --%>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn-lg btn-success pull-right">Registrovat</button>
            </div>
        </div>

    </form:form>
</div>

    </jsp:attribute>

</t:genericPageFront>