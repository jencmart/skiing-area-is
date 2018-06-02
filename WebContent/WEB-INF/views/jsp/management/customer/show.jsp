<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextUrl" value="/management/customer"/>

<t:genericPageManagment title="SLA | Objednavka detail" viewname="customer">

    <jsp:attribute name="header">


        <sec:csrfInput/>

   <div class="col-lg-12">
       <h1>Zakaznik</h1>
   </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Zakaznik</li>
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


        <div class="row">
            <div class="col-lg-6">
                <div class="row">
                    <label class="col-sm-3">Jmeno</label>
                    <div class="col-sm-10">
                            ${slaCustomer.name}
                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="row">
                    <label class="col-sm-3">Prijmeni</label>
                    <div class="col-sm-10">
                            ${slaCustomer.surname}
                    </div>
                </div>
            </div>

        </div>

<div class="row">


    <div class="col-lg-6">
        <div class="table-responsive">

            <table class="table table-bordered table-hover tablesorter">
                <thead>
                <tr>
                    <th>Cislo objednavky<i class="fa fa-sort"></i></th>
                    <th>Stav<i class="fa fa-sort"></i></th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="order" items="${slaCustomer.orders}">

                    <tr>
                        <td>${order.idOrder}</td>

                        <td>
                             <c:choose>
                                       <c:when test="${order.paid}">
                                           <span class="label label-success">Zaplaceno</span>
                                     </c:when>

                                     <c:when test="${!order.paid}">
                                           <span class="label label-danger">Nezaplaceno</span>
                                     </c:when>
                               </c:choose>
                        </td>

                        <td>
                            <spring:url value="/management/order/${order.idOrder}" var="detailUrl"/>
                            <a class="btn  btn-xs btn-success" href="${detailUrl}">
                                <i class="fa fa-search  fa-xs "></i> Detail</a>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>


</div>


    </jsp:attribute>

</t:genericPageManagment>