<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextUrl" value="/management/chipcard"/>

<t:genericPageManagment title="SLA | Index" viewname="index">

    <jsp:attribute name="header">
         <spring:url value="${contextUrl}/add" var="addOrderUrl"/>

    <div class="col-lg-12">
        <h1>Uvod
            <small>prehled</small>
        </h1>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Uvod</li>

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

    <jsp:attribute name="filter">

            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">Celkovy pocet:</div>
                    <div class="panel-body">
                        <div class="list-group">
                            <div class="list-group-item">
                                Zakazniku: <span class="badge">${cntCustomer}</span>
                            </div>
                            <div class="list-group-item">
                                Zamestnanancu: <span class="badge">${cntEmployee}</span>
                            </div>
                            <div class="list-group-item">
                                Registrovanych uzivatelu: <span class="badge">${cntUser}</span>
                            </div>
                            <div class="list-group-item">
                                Cipovych karet: <span class="badge">${cntChipCard}</span>
                            </div>
                            <div class="list-group-item">
                                Objednavek: <span class="badge">${cntOrder}</span>
                            </div>
                            <div class="list-group-item">
                                Pocet skipasu v eshopu: <span class="badge">${cntSkipass}</span>
                            </div>
                            <div class="list-group-item">
                                Zkoupenych skipassu: <span class="badge">${cntArticles}</span>
                            </div>
                            <div class="list-group-item">
                                Cena vsech zakoupenych pernamentek: <span class="badge">${priceArticles} Kc</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </jsp:attribute>

    <jsp:attribute name="content">


    <div class="col-lg-6">


    </div>


    </jsp:attribute>

</t:genericPageManagment>