<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page session="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<t:genericPageManagment title="SLA | Skipass" viewname="Skipass">

    <jsp:attribute name="header">

    <div class="col-lg-12">
        <c:choose>
            <c:when test="${slaSkipassForm['new']}">
                <h1>Skipass
                    <small>Pridat skipass</small>
                </h1>
            </c:when>
            <c:otherwise>
                <h1>Skipass
                    <small>Upravit skipass</small>
                </h1>
            </c:otherwise>
	    </c:choose>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Skipass</li>
            <li class="active"><i class="fa fa-edit"></i> Uprava</li>
        </ol>
    </div>

    </jsp:attribute>

    <jsp:attribute name="filter">

            <div class="col-lg-6"></div>

    </jsp:attribute>

    <jsp:attribute name="content">


    <div class="col-lg-6">

        <spring:url value="/management/skipass" var="checkoutConfirmAction"/>


            <%--FORM --%>
        <form:form class="form-horizontal" method="post" modelAttribute="slaSkipassForm"
                   action="${checkoutConfirmAction}">

		<form:hidden path="idSkipass"/>

            <%-- Cena --%>
            <c:set var="from1" value="price"/>
            <spring:bind path="${from1}">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
              <label class="col-sm-2 control-label">Cena</label>
              <div class="col-sm-10">
				<form:input path="${from1}" type="text" class="form-control"
                            id="${from1}" placeholder="zadejte cenu skipassu"/>
                  <form:errors path="${from1}" class="control-label"/>
              </div>
          </div>
		</spring:bind>

            <%--Pocet dnu --%>
            <c:set var="from1" value="numberOfDays"/>
            <spring:bind path="${from1}">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
              <label class="col-sm-2 control-label">Pocet dnu</label>
              <div class="col-sm-10">
				<form:input path="${from1}" type="text" class="form-control"
                            id="${from1}" placeholder="zadejte pocet dnu"/>
                  <form:errors path="${from1}" class="control-label"/>
              </div>
          </div>
		</spring:bind>


            <%--ACTION BUTTONS --%>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${slaSkipassForm['new']}">
			     <button type="submit" class="btn-lg btn-success pull-right">Pridat
                 </button>
			  </c:when>
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-success pull-right">Ulozit
                 </button>
			  </c:otherwise>
			</c:choose>
                </div>
            </div>

            <sec:csrfInput/>
	</form:form>


    </div>


    </jsp:attribute>

</t:genericPageManagment>

