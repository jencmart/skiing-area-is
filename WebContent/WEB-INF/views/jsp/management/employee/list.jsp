<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var = "contextUrl" value="/management/employee"/>

<t:genericPageManagment title="SLA | Zamestnanci" viewname="employee">

    <jsp:attribute name="header">

    <div class="col-lg-12">
        <h1>Zamestnanci
            <small>Sprava zamestnancu</small>
        </h1>
    </div>

    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li class="active"><i class="fa fa-edit"></i> Zamestnanci</li>
            <li class="active">
                <a href="#" class="btn btn-success find">  Pridat zamestnance</a>
            </li>
        </ol>
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
                    <th>Telefon <i class="fa fa-sort"></i></th>
                    <th>Pozice <i class="fa fa-sort"></i></th>
                    <th>User Role <i class="fa fa-sort"></i></th>
                    <th>Moznosti <i class=""></i></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="employee" items="${slaEmployees}">

                    <tr>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td>${employee.phone}</td>
                        <td>${employee.job.title}</td>
                        <td>
                            <c:forEach var="x" items="${employee.user}">

                                <c:forEach var="y" items="${x.userRoles}">
                                    <span class="label label-warning">${y.role.roleName}</span>
                                </c:forEach>
                            </c:forEach>
                        </td>

                        <td>
                            <spring:url value="${contextUrl}/${employee.idEmployee}" var="detailUrl"/>
                            <spring:url value="${contextUrl}/${employee.idEmployee}/update" var="updateUrl" />

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
                <h4 class="modal-title">Pridat zamestnance</h4>
            </div>
    <div class="modal-body">

        <spring:url value="/management/employee" var="saveEmployee"/>

            <%--FORM --%>
        <form:form class="form-horizontal" method="post" modelAttribute="employeeForm" action="${saveEmployee}">

        <%-- JMENO --%>
           <c:set var="from1" value="name"/>
        <spring:bind path="${from1}">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Jmeno</label>
                <div class="col-sm-10">
                    <form:input path="${from1}" type="text" class="form-control"
                                id="${from1}" placeholder="zadejte jmeno"/>
                    <form:errors path="${from1}" class="control-label"/>
                </div>
            </div>
        </spring:bind>

            <%--PRIJMENI --%>
            <c:set var="from1" value="surname"/>
        <spring:bind path="${from1}">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Prijmeni</label>
                <div class="col-sm-10">
                    <form:input path="${from1}" class="form-control"
                                id="${from1}" placeholder="Zadejte prijmeni"/>
                    <form:errors path="${from1}" class="control-label"/>
                </div>
            </div>
        </spring:bind>

            <%--email --%>
            <c:set var="from1" value="phone"/>
        <spring:bind path="${from1}">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Telefon</label>
                <div class="col-sm-10">
                    <form:input path="${from1}" class="form-control"
                                id="${from1}" placeholder="Zadejte telefon"/>
                    <form:errors path="${from1}" class="control-label"/>
                </div>
            </div>
        </spring:bind>

            <%--USERNAME  --%>
            <c:set var="from1" value="username"/>
        <spring:bind path="${from1}">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Username</label>
                <div class="col-sm-10">
                    <form:input path="${from1}" class="form-control"
                                id="${from1}" placeholder="Zadejte username"/>
                    <form:errors path="${from1}" class="control-label"/>
                </div>
            </div>
        </spring:bind>

            <%--PASSWORD  --%>
            <c:set var="from1" value="password"/>
        <spring:bind path="${from1}">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Heslo</label>
                <div class="col-sm-10">
                    <form:input path="${from1}" class="form-control"
                                id="${from1}" placeholder="Zadejte heslo"/>
                    <form:errors path="${from1}" class="control-label"/>
                </div>
            </div>
        </spring:bind>

            <%--PASSWORD  CONF --%>
            <c:set var="from1" value="passwordConfirm"/>
        <spring:bind path="${from1}">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Heslo znovu</label>
                <div class="col-sm-10">
                    <form:input path="${from1}" class="form-control"
                                id="${from1}" placeholder="Zadejte heslo pro kontrolu"/>
                    <form:errors path="${from1}" class="control-label"/>
                </div>
            </div>
        </spring:bind>


            <spring:bind path="job">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Povolani</label>
                <div class="col-sm-5">
                    <form:select path="job" class="form-control">
                        <form:option value="NONE" label="--- Vyber ---" />
                        <form:options items="${jobList}" />
                    </form:select>
                    <form:errors path="job" class="control-label" />
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>


            <%--LANGUAGES  --%>
            <c:set var="from1" value="foreignLanguages"/>
        <spring:bind path="${from1}">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Cizi jazyky</label>
                <div class="col-sm-10">
                    <form:input path="${from1}" class="form-control"
                                id="${from1}" placeholder="Zadejte cizi jazyky"/>
                    <form:errors path="${from1}" class="control-label"/>
                </div>
            </div>
        </spring:bind>


            <sec:csrfInput/>
        <button type="submit" class="btn-lg btn-success pull-right">Vytvorit
        </button>

        </form:form>



    </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Zavrit</button>
            </div>
        </div>

    </div>
</div>


    </jsp:attribute>

</t:genericPageManagment>