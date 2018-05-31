<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:genericPageFront viewname="index">

    <jsp:attribute name="frontbody">
    <!-- Jumbotron Header -->
    <header class="jumbotron my-4">
        <h1 class="display-3">Uzivatelsky profil: </h1>
    </header>

    <!-- Page Features -->


           <div class="row text-center">

               <div class="table-responsive">
                   <table class="table table-bordered table-hover tablesorter">
                       <thead>
                       <tr>
                           <th>Objednavka c. <i class="fa fa-sort"></i></th>
                           <th>Celkova cena <i class="fa fa-sort"></i></th>
                       </tr>
                       </thead>

                       <tbody>

                       <c:forEach var="order" items="${orders}">
                           <tr>
                               <td>${order.key.idOrder}</td>
                               <td>${order.value} Kc</td>
                           </tr>
                        </c:forEach>
                       </tbody>
                   </table>

               </div>


           </div>

    <!-- /.row -->

    </jsp:attribute>

</t:genericPageFront>