<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericPageFront viewname="shoppingcart">

    <jsp:attribute name="frontbody">
    <!-- Jumbotron Header -->
    <header class="jumbotron my-4">
        <h1 class="display-3">Rekapitualce: </h1>
    </header>

    <!-- Page Features -->

     <!-- order sumarry -->
    <div class="row text-center">

        <div class="table-responsive">

            <table class="table table-bordered table-hover tablesorter">
                <thead>
                <tr>
                    <th>Polozka <i class="fa fa-sort"></i></th>
                    <th>Pocet dnu <i class="fa fa-sort"></i></th>
                    <th>Cena <i class=""></i></th>
                    <th>Pocet kusu <i class=""></i></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="item" items="${items}">

                            <tr id="${item.skipass.idSkipass}">
                                <td>Skipass</td>
                                <td>${item.skipass.numberOfDays}</td>
                                <td><span class="item-price">${item.price}</span> Kc</td>
                                <td>${item.count}</td>
                            </tr>

                        </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

        <!-- total price -->

             <div class="row justify-content-end text-right">
                 <div class="col-4">
                   <span class="label label-success">Cena celkem:
                <span id="total-price">${totalPrice}</span> Kc
                    </span>
                 </div>
             </div>


        <!-- go back -->
            <div class="row ">

                <div class="col-4">
                    <a class="btn   btn-success " href="/shoppingcart">
                        <i class="fa fa-arrow-left"></i> Zpet
                    </a>
                </div>
            </div>

     <div>


         <!-- billing details -->
         <spring:url value="/shoppingcart/checkout" var="checkoutConfirmAction"/>

         <form:form class="form-horizontal" method="post" modelAttribute="customerOrderForm"
                    action="${checkoutConfirmAction}">


    <%-- Name --%>
             <c:set var="from1" value="name"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Jmeno</label>
                    <div class="col-sm-10">
                <form:input path="name" type="text" class="form-control"
                            id="${from1}" placeholder="Jmeno"/>
                        <form:errors path="name" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

             <%-- Surname --%>
             <c:set var="from1" value="surname"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Prijmeni</label>
                    <div class="col-sm-10">
                <form:input path="surname" type="text" class="form-control"
                            id="${from1}" placeholder="Prijmeni"/>
                        <form:errors path="surname" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

             <%-- Email --%>
             <c:set var="from1" value="email"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                <form:input path="email" type="text" class="form-control"
                            id="${from1}" placeholder="Email"/>
                        <form:errors path="email" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

             <%-- Phone --%>
             <c:set var="from1" value="phone"/>
    <spring:bind path="${from1}">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Telefon</label>
                    <div class="col-sm-10">
                <form:input path="phone" type="text" class="form-control"
                            id="${from1}" placeholder="Telefon"/>
                        <form:errors path="phone" class="control-label"/>
                    </div>
                </div>
    </spring:bind>

        <sec:csrfInput/>

             <%--ACTION BUTTONS --%>
             <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">
                     <button type="submit" class="btn-lg btn-success pull-right">Dokoncit objednavku</button>
                 </div>
             </div>

    </form:form>
     </div>





    <!-- /.row -->

    </jsp:attribute>

</t:genericPageFront>