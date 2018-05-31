<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<t:genericPageFront viewname="login">

    <jsp:attribute name="frontbody">

        <div class="jumbotron my-4  col-centered">

            <h1 class="display-4">Prihlaseni uzivatele</h1>
            <hr class="my-4">

            <spring:url value="/login" var="checkoutConfirmAction"/>


            <form:form class="form-horizontal" method="post" modelAttribute="customerUserLoginFrom"
                       action="${checkoutConfirmAction}">

    <%-- username --%>
    <c:set var="from1" value="username"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                <form:input path="${from1}" type="text" class="form-control"
                            id="${from1}" placeholder="username"/>
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
                <sec:csrfInput/> <%--cross site request forgery prevention --%>

                <%--ACTION BUTTONS --%>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn-lg btn-success pull-right">Prihlasit se</button>
                    </div>
                </div>

    </form:form>
        </div>

    </jsp:attribute>

</t:genericPageFront>