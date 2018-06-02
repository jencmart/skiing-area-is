<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextUrl" value="/management/customer"/>

<t:genericPageManagment title="SLA | Zkakaznik" viewname="customer">

    <jsp:attribute name="header">



    <div class="col-lg-12">
        <h1>Zakaznici
            <small>Sprava zakazniku</small>
        </h1>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Zakaznici</li>

            <li class="active">
                <a href="#" class="btn btn-warning find">Vyhledat zakaznika</a>
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

            </div>

    </jsp:attribute>

    <jsp:attribute name="content">


    <div class="col-lg-6">
        <div class="table-responsive">

            <table class="table table-bordered table-hover tablesorter">
                <thead>
                <tr>
                    <th>Jmeno <i class="fa fa-sort"></i></th>
                    <th>Prijmeni <i class="fa fa-sort"></i></th>
                    <th>Email <i class="fa fa-sort"></i></th>
                    <th>Telefon <i class="fa fa-sort"></i></th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="customer" items="${slaCustomers}">

                    <tr>
                        <td>${customer.name}</td>

                        <td>${customer.surname}</td>

                        <td>${customer.email}</td>

                        <td>${customer.phone}</td>

                        <td>

                            <spring:url value="${contextUrl}/${customer.id_customer}" var="detailUrl"/>

                            <a class="btn  btn-xs btn-success" href="${detailUrl}">
                                <i class="fa fa-search  fa-xs "></i> Detail</a>

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
                <h4 class="modal-title">Vyhledani zakaznika</h4>
            </div>
            <div class="modal-body">

                <spring:url value="${contextUrl}/find" var="findUrl"/>

                <form action="${findUrl}" method="get">

                    <input hidden name="id_customer">
                    <p>Udaje</p>

                    <div class="form-group">
                        <label for="name">Jmeno</label>
                        <input class="form-control" id="name" name="name" placeholder="Jmeno">
                    </div>

                    <div class="form-group">
                        <label for="surname">Prijmeni</label>
                        <input class="form-control" id="surname" name="surname" placeholder="Prijmeni">
                    </div>


                    <div class="form-group">
                        <label for="phone">Telefon</label>
                        <input class="form-control" id="phone" name="phone" placeholder="Telefon">
                    </div>


                    <div class="form-group">
                        <label for="email">E-mail</label>
                        <input class="form-control" id="email" name="email" placeholder="E-mail">
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