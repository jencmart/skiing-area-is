<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextUrl" value="/management/skipass"/>

<t:genericPageManagment title="SLA | Skipass" viewname="skipass">

    <jsp:attribute name="header">


         <spring:url value="${contextUrl}/add" var="addOrderUrl"/>

    <div class="col-lg-12">
        <h1>Skipassy
            <small>Sprava skipassu</small>
        </h1>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Skipassy</li>
            <li class="active">
                <a href="${addOrderUrl}" class="btn btn-success">Pridat skipass</a>
            </li>
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
                    <div class="panel-heading">Filters</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="checkbox">
                                <label>
                                    TODO...
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </jsp:attribute>

    <jsp:attribute name="content">


    <div class="col-lg-6">
        <div class="table-responsive">

            <table class="table table-bordered table-hover tablesorter">
                <thead>
                <tr>
                    <th>Cena <i class="fa fa-sort"></i></th>
                    <th>Pocet dnu <i class="fa fa-sort"></i></th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="chipCard" items="${slaSkipass}">

                    <tr>
                        <td>${chipCard.price} Kc</td>

                        <td>${chipCard.numberOfDays}</td>

                        <!-- options todo -->
                        <td>

                            <spring:url value="${contextUrl}/${chipCard.idSkipass}" var="detailUrl"/>
                            <spring:url value="${contextUrl}/${chipCard.idSkipass}/update" var="updateUrl"/>
                            <spring:url value="${contextUrl}/${chipCard.idSkipass}/delete" var="deleteUrl"/>
                            <form action="${deleteUrl}" method="post">
                                <a class="btn  btn-xs btn-success" href="${detailUrl}">
                                    <i class="fa fa-search  fa-xs "></i> Detail</a>

                                <button class="btn btn-xs btn-danger"
                                        type="submit"><i class="fa fa-trash  fa-xs "></i> Smazat
                                </button>
                                <sec:csrfInput/>
                            </form>

                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>


    </jsp:attribute>

</t:genericPageManagment>