<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var = "contextUrl" value="/management/order"/>

<t:genericPageManagment title="SLA | Objednavka detail" viewname="order">

    <jsp:attribute name="header">


        <sec:csrfInput/>

   <div class="col-lg-12">
       <h1>Objednavka</h1>
   </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Objednavka</li>
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
            <label class="col-sm-3">Zakaznik</label>
            <div class="col-sm-10">
            <c:if test="${empty slaOrder.customer}">
                        POKLADNA
                        </c:if>

            <c:if test="${not empty slaOrder.customer}">
                        ${slaOrder.customer.name} ${slaOrder.customer.surname}
                        </c:if>

           </div>
        </div>


        <div class="row">
            <label class="col-sm-3">Celkova cena</label>
            <div class="col-sm-10">
                    ${price} Kc
            </div>
        </div>
    </div>

        <div class="row">
            <spring:url value="${contextUrl}/${slaOrder.idOrder}/pay" var="payUrl"/>
            <form action = "${payUrl}" method = "post">
                <c:if test="${slaOrder.paid == false}">
                <button class="btn btn-danger"
                        type="submit"> <i class="fa fa-money  fa-xs "></i> Zaplatit</button>
                <sec:csrfInput/>
                </c:if>
            </form>
        </div>

        </div>

<div class="row">


    <div class="col-lg-6">
        <div class="table-responsive">

            <table class="table table-bordered table-hover tablesorter">
                <thead>
                <tr>
                    <th>cena skipasu<i class="fa fa-sort"></i></th>
                    <th>Stav skipasu<i class="fa fa-sort"></i></th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="article" items="${articles}">

                    <tr>
                        <td>${article.skipass.price}</td>

                        <td>
                             <c:choose>
                                     <c:when test = "${article.cardRented && ! article.cardReturned}">
                                           <span class="label label-danger">RFID Zapujcena</span>
                                     </c:when>

                                     <c:when test = "${article.cardRented && article.cardReturned}">
                                           <span class="label label-success">RFID Vracena</span>
                                     </c:when>

                                     <c:otherwise>
                                        <span class="label label-warning">RFID nezapujcena</span>
                                     </c:otherwise>
                               </c:choose>
                        </td>

                        <td>
                            <spring:url value="${contextUrl}/${slaOrder.idOrder}/rent/${article.idOrderSkipasArticle}" var="rentUrl"/>
                            <spring:url value="${contextUrl}/${slaOrder.idOrder}/return/${article.idOrderSkipasArticle}/update" var="returnUrl" />
                            <spring:url value="${contextUrl}/${slaOrder.idOrder}/lost/${article.idOrderSkipasArticle}/delete" var="lostUrl" />

                      <c:choose>
                          <c:when test = "${! article.cardRented}">
                                    <a class="btn  btn-xs btn-warning rent" data-order="${slaOrder.idOrder}" data-article="${article.idOrderSkipasArticle}" href="#">Zapujcit RFID <i class="fa fa-arrow-right  fa-xs "></i></a>
                                     </c:when>

                                     <c:otherwise>

                                         <a class="btn  btn-xs btn-success return" data-order="${slaOrder.idOrder}" data-article="${article.idOrderSkipasArticle}" href="#"><i class="fa  fa-arrow-left  fa-xs "></i> Vratit RFID</a>
                                         <a class="btn  btn-xs btn-danger lost" data-order="${slaOrder.idOrder}" data-article="${article.idOrderSkipasArticle}"  href="#"><i class="fa  fa-trash  fa-xs "></i> Ztrata </a>
                                     </c:otherwise>
                               </c:choose>

                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>



</div>

        <!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Zapujceni RFID karty</h4>
            </div>
            <div class="modal-body">



                <form id="target" action="">

                    <p>Naskenujte cipovou kartu.</p>

                    <div class="form-group">
                        <label for="rfid">RFID</label>
                        <input  class="form-control" id="rfid" placeholder="Zadejte RFID">
                    </div>

                    <input type="hidden" name="orderid" value="">
                    <input type="hidden" name="articleid" value="">

                    <button type="submit" class="btn btn-primary" id="find">Vyhledat RFID kartu</button>
                </form>

                <form id="secondary" action="">

                    <div class="form-group">
                        <label> Cena zalohy:
                            <input class="form-control" name="deposit" disabled value="">
                        </label>
                        <input type="hidden" name="idchipcard" value="">
                    </div>

                    <button type="submit" class="btn btn-primary" id="assign">Zapujcit RFID kartu</button>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Zrusit</button>
            </div>
        </div>

    </div>
</div>


    </jsp:attribute>

</t:genericPageManagment>