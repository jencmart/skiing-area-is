<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:genericPageFront viewname="shoppingcart">

    <jsp:attribute name="frontbody">
    <!-- Jumbotron Header -->
    <header class="jumbotron my-4">
        <h1 class="display-3">Obsah nakupniho kosiku: </h1>
    </header>

    <!-- Page Features -->

        <spring:url value="/resources/images/carousel.jpg" var="imgResource"/>


        <sec:csrfInput/>

           <div class="row text-center">

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

                       <tbody>

                       <c:forEach var="item" items="${items}">

                            <tr id="${item.skipass.idSkipass}">

                                <td>Skipass</td>

                                <td>${item.skipass.numberOfDays}</td>

                                <td><span class="item-price">${item.price}</span> Kc</td>

                                <td>
                                    <div class="input-group col-sm-5">
                                    <span class="input-group-btn btn-group-xs">
                                          <button type="button" class="btn btn-danger btn-number btn-xs"
                                                  data-type="minus" data-field="quant[${item.skipass.idSkipass}]">
                                            <span class="fa fa-minus fa-xs"></span>
                                          </button>
                                    </span>

                                        <input type="text" disabled="disabled" name="quant[${item.skipass.idSkipass}]"
                                               class="form-control input-number" value="${item.count}" min="1"
                                               max="100">

                                        <span class="input-group-btn">
                                          <button type="button" class="btn btn-success btn-number btn-xs"
                                                  data-type="plus" data-field="quant[${item.skipass.idSkipass}]">
                                              <span class="fa fa-plus fa-xs"></span>
                                          </button>
                                    </span>
                                    </div>
                                </td>

                                <td>
                                    <a class="btn  btn-xs btn-danger btn-delete-item" href="#">
                                        <i class="fa fa-trash fa-xs"></i> Odstranit !</a>
                                </td>
                            </tr>

                        </c:forEach>

                       </tbody>

                   </table>

               </div>


           </div>

         <div class="row justify-content-end text-right">
             <div class="col-4">
                   <span class="label label-success">Cena celkem:
                <span id="total-price">${totalPrice}</span> Kc
                    </span>
             </div>

         </div>

               <div class="row justify-content-end text-right">

                   <div class="col-4">
                       <a class="btn   btn-success " href="/shoppingcart/checkout">
                           Pokracovat <i class="fa fa-arrow-right"></i>
                       </a>
                   </div>
               </div>


    <!-- /.row -->

    </jsp:attribute>

</t:genericPageFront>