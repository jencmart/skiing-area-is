<%@ page contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:genericPageFront viewname="products">

    <jsp:attribute name="frontbody">
    <!-- Jumbotron Header -->
    <header class="jumbotron my-4">
        <h1 class="display-3">Produkty:</h1>

    </header>

    <!-- Page Features -->



        <spring:url value="/resources/images/carousel.jpg" var="imgResource"/>


           <div class="row text-center">

        <c:forEach var="skipass" items="${skipassess}">


               <div class="col-lg-3 col-md-6 mb-4">
                   <div class="card">
                       <img class="card-img-top" src="${imgResource}" alt="">
                       <div class="card-body">
                           <h4 class="card-title">Skipass</h4>
                           <p class="card-text">Nake velmi zajimave info o skipassu. <br/> Pocet
                               dnu: ${skipass.numberOfDays} <br/> Cena: ${skipass.price} Kc</p>
                       </div>
                       <div class="card-footer">
                           <a class="btn btn-success skipassBtn" href="#"
                              data-idskipass="${skipass.idSkipass}">Koupit!</a>
                           <sec:csrfInput/>
                       </div>
                   </div>
               </div>



        </c:forEach>


           </div>


    <!-- /.row -->

    </jsp:attribute>

</t:genericPageFront>