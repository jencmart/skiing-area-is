<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var = "contextUrl" value="/management/order"/>

<t:genericPageManagment title="SLA | Objednavky" viewname="order">

    <jsp:attribute name="header">


         <spring:url value="${contextUrl}/new" var="addOrderUrl"/>


        <spring:url value="${contextUrl}/find" var="findOrderUrl"/>

    <div class="col-lg-12">
        <h1>Objednavky
            <small>Sprava objednavek</small>
        </h1>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Objednavky</li>
            <li class="active">
                <a href="${addOrderUrl}" class="btn btn-success">Nova objednavka</a>
            </li>

            <li class="active">
                <a href="#" class="btn btn-warning find">Vyhledat objednavku</a>
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
                    <th>C. objednavky<i class="fa fa-sort"></i></th>
                    <th>Zakaznik <i class="fa fa-sort"></i></th>
                    <th>Zaplaceno <i class="fa fa-sort"></i></th>
                    <th>Vytvoreno </th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="order" items="${slaOrders}">

                    <tr>
                        <td>${order.idOrder}</td>

                        <c:if test="${empty order.customer}">
                        <td> POKLADNA </td>
                        </c:if>

                        <c:if test="${not empty order.customer}">
                        <td>
                            <a href="/management/customer/${order.customer.id_customer}">${order.customer.name} ${order.customer.surname}</a>

                        </td>
                        </c:if>

                        <td>
                             <c:if test="${order.paid == true}">
                                <span class="label label-success">Ano</span>
                            </c:if>
                            <c:if test="${order.paid == false}">
                                <span class="label label-danger">Ne</span>
                            </c:if>
                        </td>


                        <td>${order.created}</td>

                        <td>
                            <spring:url value="${contextUrl}/${order.idOrder}" var="detailUrl"/>
                            <spring:url value="${contextUrl}/${order.idOrder}/pay" var="payUrl"/>
                            <form action = "${payUrl}" method = "post">
                                <a class="btn  btn-xs btn-success" href="${detailUrl}">
                                    <i class="fa fa-search  fa-xs "></i> Detail</a>
                                <c:if test="${order.paid == false}">
                                <button class="btn btn-xs btn-danger"
                                        type="submit"> <i class="fa fa-money  fa-xs "></i> Zaplatit</button>
                                <sec:csrfInput/>
                                </c:if>
                            </form>

                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>





        <!-- Modal -->
<div id="find" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Vyhledani Objednavky</h4>
            </div>
            <div class="modal-body">

                <form action = "${findOrderUrl}" method = "get">

                    <p>Zadejte cislo objednavky.</p>

                    <div class="form-group">
                        <label for="number">Cislo objednavky</label>
                        <input  class="form-control" id="number" name="number" placeholder="Cislo objednavky">
                    </div>
                    <sec:csrfInput/>
                    <button type="submit" class="btn btn-primary" id="find">Vyhledat</button>
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