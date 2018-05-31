<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var = "contextUrl" value="/management/skipass"/>

<t:genericPageManagment title="SLA | Skipass detail" viewname="skipass">

    <jsp:attribute name="header">

   <div class="col-lg-12">
       <h1>Skipass</h1>
   </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Skipass</li>
            <li class="active"><i class="fa fa-edit"></i> Detail</li>
        </ol>
    </div>

        <div class="col-lg-6">
        	<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"
                        aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <strong>${msg}</strong>
            </div>
		</c:if>
        </div>

    </jsp:attribute>


    <jsp:attribute name="content">

    <div class="col-lg-6">
        <div class="row">
            <label class="col-sm-3">Cena</label>
            <div class="col-sm-10">${slaSkipass.price}</div>
        </div>


        <div class="row">
            <label class="col-sm-3">Pocet dnu</label>
            <div class="col-sm-10">${slaSkipass.numberOfDays}</div>
        </div>


        <div class="row">
            <spring:url value="${contextUrl}" var="listUrl"/>
            <spring:url value="${contextUrl}/${slaSkipass.idSkipass}/update" var="updateUrl" />
            <spring:url value="${contextUrl}/${slaSkipass.idSkipass}/delete" var="deleteUrl" />

            <form action = "${deleteUrl}" method = "post">
                <a class="btn  btn-primary" href="${listUrl}">
                    <i class="fa fa-angle-left  fa-sm "></i> Zpet</a>

                <a class="btn  btn-warning" href="${updateUrl}">
                    <i class="fa fa-edit  fa-sm "></i> Upravit</a>

                <sec:csrfInput/>
                <button class="btn btn-danger"
                        type="submit"> <i class="fa fa-trash fa-sm "></i> Smazat</button>
            </form>

        </div>

    </div>


    </jsp:attribute>

</t:genericPageManagment>