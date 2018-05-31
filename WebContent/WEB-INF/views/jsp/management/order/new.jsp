<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var = "contextUrl" value="/management/order/new"/>

<t:genericPageManagment title="SLA | Objednavky" viewname="order">
    <jsp:attribute name="header">
        <sec:csrfInput/>

    <div class="col-lg-12">
        <h1>Objednavky
            <small>Nova objednavka</small>
        </h1>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Objednavky</li>
            <li class="active">
                Nova objednavka
            </li>

            <li class="active">
                <a href="#" id="additems" class="btn btn-success">Pridat polozky do objednavky</a>
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


    <jsp:attribute name="content">

    <div class="col-lg-6">

        <div class="table-responsive">

            <table class="table table-bordered table-hover tablesorter">
                <thead>
                <tr>
                    <th>Polozka <i class="fa fa-sort"></i></th>
                    <th>Pocet dnu <i class="fa fa-sort"></i></th>
                    <th>Cena <i class=""></i></th>
                    <th>Pocet kusu <i class=""></i></th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>

                <tbody id="tableOrders">

                <c:forEach var="item" items="${items}">

                            <tr  id="${item.skipass.idSkipass}">

                                <td>Skipass</td>

                                <td>${item.skipass.numberOfDays}</td>

                                <td ><span class="item-price">${item.price}</span> Kc</td>

                                <td>
                                    <div class="input-group col-sm-5">
                                    <span class="input-group-btn btn-group-xs">
                                          <button type="button" class="btn btn-danger btn-number btn-xs"  data-type="minus" data-field="quant[${item.skipass.idSkipass}]">
                                            <span class="fa fa-minus fa-xs"></span>
                                          </button>
                                    </span>

                                        <input type="text" disabled="disabled" name="quant[${item.skipass.idSkipass}]" class="form-control input-number" value="${item.count}" min="1" max="100">

                                        <span class="input-group-btn">
                                          <button type="button" class="btn btn-success btn-number btn-xs"   data-type="plus"  data-field="quant[${item.skipass.idSkipass}]">
                                              <span class="fa fa-plus fa-xs"></span>
                                          </button>
                                    </span>
                                    </div>
                                </td>

                                <td>
                                    <a class="btn  btn-xs btn-danger btn-delete-item" href="#" >
                                        <i class="fa fa-trash fa-xs"></i> Odstranit !</a>
                                </td>
                            </tr>

                        </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="row justify-content-end text-right" >
            <div class="col-4">
                   <span class="label label-success" >Cena celkem:
                <span id="total-price" >${totalPrice}</span> Kc
                    </span>
            </div>

        </div>


        <div>
            <spring:url value="/management/order/new/checkout" var="checkoutConfirmAction"/>
            <form class="form-horizontal" method="post" action="${checkoutConfirmAction}">

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn-lg btn-success pull-right">Potvrdit objednavku</button>
                    </div>
                </div>
                <sec:csrfInput/>
            </form>

        </div>


    </div>


        <!-- Modal -->
<div id="addArticlesModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Skipassy ktere je moze nakoupit</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-hover tablesorter">
                    <thead>
                    <tr>
                        <th>Artikl <i class="fa fa-sort"></i></th>
                        <th>Pocet dnu <i class="fa fa-sort"></i></th>
                        <th>Cena <i class=""></i></th>
                        <th>Akce <i class=""></i></th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var="skipass" items="${skipasses}">

                            <tr  id="${skipass.idSkipass}">
                                <td>Skipass</td>
                                <td>${skipass.numberOfDays}</td>
                                <td ><span>${skipass.price}</span> Kc</td>
                                <td>    <a class="btn btn-success btn-xs skipassBtn" href="#" data-idskipass="${skipass.idSkipass}" data-price="${skipass.price}" data-numdays="${skipass.numberOfDays}"  >Pridat!</a>     </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Hotovo</button>
            </div>
        </div>

    </div>
</div>


    </jsp:attribute>

</t:genericPageManagment>