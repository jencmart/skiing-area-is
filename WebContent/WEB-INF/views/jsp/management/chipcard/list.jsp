<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextUrl" value="/management/chipcard"/>

<t:genericPageManagment title="SLA | Chipcard" viewname="chipcard">

    <jsp:attribute name="header">


         <spring:url value="${contextUrl}/add" var="addOrderUrl"/>

        <spring:url value="${contextUrl}/return" var="returnCardUrl"/>

    <div class="col-lg-12">
        <h1>Cipove karty
            <small>Sprava cipovych karet</small>
        </h1>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Cipove karty</li>
            <li class="active">
                <a href="${addOrderUrl}" class="btn btn-success">Přidat kartu</a>
                <a href="#" class="btn btn-warning returnCard"> <i class="fa fa-arrow-left "></i> Vratit kartu</a>
            </li>

            <li class="active">

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
                    <th>RFID <i class="fa fa-sort"></i></th>
                    <th>Registrovana <i class="fa fa-sort"></i></th>
                    <th>Odstranena <i class="fa fa-sort"></i></th>
                    <th>Zapujcena <i class="fa fa-sort"></i></th>
                    <th>Zaloha <i class="fa fa-sort"></i></th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="chipCard" items="${slaChipCards}">

                    <tr>
                        <td>${chipCard.rfidId}</td>

                        <td>${chipCard.registeredTimestamp}</td>

                        <td>
                            <c:if test="${not empty chipCard.removed}">
                                <span class="label label-warning">Ano</span>
                            </c:if>
                            <c:if test="${empty chipCard.removed}">
                                <span class="label label-success">Ne</span>
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${chipCard.cardRented == true}">
                                <span class="label label-warning">Ano</span>
                            </c:if>
                            <c:if test="${chipCard.cardRented == false}">
                                <span class="label label-success">Ne</span>
                            </c:if>
                        </td>


                        <td>${chipCard.depositPrice} Kc</td>

                        <!-- options todo -->
                        <td>

                            <spring:url value="${contextUrl}/${chipCard.idChipCard}" var="detailUrl"/>
                            <spring:url value="${contextUrl}/${chipCard.idChipCard}/update" var="updateUrl"/>
                            <spring:url value="${contextUrl}/${chipCard.idChipCard}/delete" var="deleteUrl"/>
                            <form action="${deleteUrl}" method="post">
                                <a class="btn  btn-xs btn-success" href="${detailUrl}">
                                    <i class="fa fa-search  fa-xs "></i> Detail</a>

                                <a class="btn  btn-xs btn-warning" href="${updateUrl}">
                                    <i class="fa fa-edit  fa-xs "></i> Upravit</a>

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

        <!-- Modal -->
<div id="returnRfid" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Vraceni RFID karty</h4>
            </div>
            <div class="modal-body">

                <form action="${returnCardUrl}" method="post">

                    <p>Naskenujte cipovou kartu.</p>

                    <div class="form-group">
                        <label for="rfid">RFID</label>
                        <input class="form-control" id="rfid" name="rfid" placeholder="Zadejte RFID">
                    </div>

                    <sec:csrfInput/>
                    <button type="submit" class="btn btn-primary" id="find">Vratit RFID kartu</button>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Zavrit</button>
            </div>
        </div>

    </div>
</div>
    </jsp:attribute>

</t:genericPageManagment>