<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var = "contextUrl" value="/management/chipcard"/>

<t:genericPageManagment title="SLA | Chipcard detail" viewname="chipcard">

    <jsp:attribute name="header">

   <div class="col-lg-12">
            <h1>Cipova Karta <small>${slaChipCard.rfidId}</small></h1>

   </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Cipove karty</li>
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
            <label class="col-sm-3">RFID</label>
            <div class="col-sm-10">${slaChipCard.rfidId}</div>
        </div>


        <div class="row">
            <label class="col-sm-3">Zaloha cena</label>
            <div class="col-sm-10">${slaChipCard.depositPrice}</div>
        </div>

        <div class="row">
            <label class="col-sm-3">Registrovna</label>
            <div class="col-sm-10">${slaChipCard.registeredTimestamp}</div>
        </div>


        <div class="row">
            <label class="col-sm-3">Odstranena</label>
            <c:choose>
              <c:when test="${not empty slaChipCard.removed}">
                  <div class="col-sm-10">  ${slaChipCard.removedTimestamp} </div>
              </c:when>
              <c:otherwise>
                  <div class="col-sm-10"> --- </div>
              </c:otherwise>
            </c:choose>
        </div>


        <div class="row">
            <spring:url value="${contextUrl}" var="listUrl"/>
            <spring:url value="${contextUrl}/${slaChipCard.idChipCard}/update" var="updateUrl" />
            <spring:url value="${contextUrl}/${slaChipCard.idChipCard}/delete" var="deleteUrl" />

            <form action = "${deleteUrl}" method = "post">
                <a class="btn   btn-primary" href="${listUrl}">
                    <i class="fa fa-angle-left  fa-sm "></i> Zpet</a>

                <a class="btn  btn-warning" href="${updateUrl}">
                    <i class="fa fa-edit  fa-sm "></i> Upravit</a>

                <button class="btn btn-danger"
                        type="submit"> <i class="fa fa-trash fa-sm "></i> Smazat</button>
                <sec:csrfInput/>
            </form>

        </div>

    </div>


    </jsp:attribute>

</t:genericPageManagment>