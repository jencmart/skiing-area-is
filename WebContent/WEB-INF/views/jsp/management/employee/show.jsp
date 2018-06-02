<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextUrl" value="/management/employee"/>

<t:genericPageManagment title="SLA | Zamestnanec detail" viewname="employee">

    <jsp:attribute name="header">

        <sec:csrfInput/>

   <div class="col-lg-12">
       <h1>Zamestnanec</h1>
   </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Zamestnanec</li>
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
                <div class="col-lg-6">
                    <div class="row">
                        <label class="col-sm-3">Jmeno</label>
                        <div class="col-sm-10">
                                ${slaEmployee.name}
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="row">
                        <label class="col-sm-3">Prijmeni</label>
                        <div class="col-sm-10">
                                ${slaEmployee.surname}
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="row">
                        <label class="col-sm-3">Telefon</label>
                        <div class="col-sm-10">
                                ${slaEmployee.phone}
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="row">
                        <label class="col-sm-3">Zamestnani</label>
                        <div class="col-sm-10">
                                ${slaEmployee.job.title}
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <spring:url value="${contextUrl}" var="listUrl"/>
                <a class="btn   btn-primary" href="${listUrl}">
                    <i class="fa fa-angle-left  fa-sm "></i> Zpet</a>

            </div>

        </div>

    </jsp:attribute>

</t:genericPageManagment>