<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 20/03/2019
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">  <%@ include file="css/editeur.css" %> </style>
    <title>Editer les sous-titres</title>
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container">
    <p>TRADUCTION</p>

    <div class="container">
        <form class="row" method="post" action="edit">
            <div class="form-group" >
                <label for="id_traduction" class="col-lg-4 control-label">Selectionner la traduction </label>
                <div class="col-lg-4">
                    <select id="id_traduction" name="id_traduction" class="form-control" >
                        <c:forEach items="${traductions}" var="traduction">
                            <option value="${traduction.id}"><c:out value="${traduction.titre}" /></option>
                        </c:forEach>
                    </select>
                </div>
                <input class="btn" type="submit" name="_ok_" value="OK" action="edit">
            </div>
        </form>

        <form method="post" action="edit">
            <div class="col-lg-6">
                <div class="row">
                    <input class="btn" type="submit" name="_export_" value="Exporter en .srt" action="">
                </div>
            </div>
        </form>

        <form method="post" action="edit">
            <div class="row col-lg-12">
                <div class="col-lg-6"></div>
                <div class="col-lg-6">
                    <p><c:out value="${ trad.titre }"/></p>
                </div>
                <div class="col-lg-6"></div>

                <div class="col-lg-6">
                    <div class="row">
                        <input class="btn" type="submit" name="_enregistrer_" value="Enregister" action="">
                    </div>
                </div>

                <div class="col-lg-12">
                    <div class="row block">
                        <div class="col-lg-1 tabTrad">n°</div>
                        <div class="col-lg-4 tabTrad">
                            <p>text original</p>
                            <c:choose>
                            <c:when test="${trad.langueOrigine == 1}">FRANCAIS</c:when>
                            <c:when test="${trad.langueOrigine == 2}">ANGLAIS</c:when>
                            <c:when test="${trad.langueOrigine == 3}">ALLEMAND</c:when>
                            <c:when test="${trad.langueOrigine == 4}">ESPAGNOL</c:when>
                            <c:when test="${trad.langueOrigine == 5}">ITALIEN</c:when>
                            <c:when test="${trad.langueOrigine == 6}">MANDARIN</c:when>
                            </c:choose>
                        </div>
                        <div class="col-lg-3 tabTrad">timer</div>
                        <div class="col-lg-4 tabTrad">
                            <p>texte traduit</p>
                            <c:choose>
                            <c:when test="${trad.langueTraduction == 1}">FRANCAIS</c:when>
                            <c:when test="${trad.langueTraduction == 2}">ANGLAIS</c:when>
                            <c:when test="${trad.langueTraduction == 3}">ALLEMAND</c:when>
                            <c:when test="${trad.langueTraduction == 4}">ESPAGNOL</c:when>
                            <c:when test="${trad.langueTraduction == 5}">ITALIEN</c:when>
                            <c:when test="${trad.langueTraduction == 6}">MANDARIN</c:when>
                            </c:choose>
                        </div>
                    </div>
                    <form>
                        <c:forEach items="${trad.blockTrads}" var="blockTrad">
                            <div class="row block">
                                <div class="col-lg-1 tabTrad"><c:out value="${blockTrad.numLigne}" /></div>
                                <div class="col-lg-4 tabTrad"><c:out value="${blockTrad.ligneOrigine_1}" /> <c:out value="${blockTrad.ligneOrigine_2}" /></div>
                                <div class="col-lg-3 tabTrad"><c:out value="${blockTrad.temps}" /></div>
                                <div class="col-lg-4 tabTrad">
                                    <input class="form-control" type="text" name="line${ blockTrad.numLigne }" id="line${ blockTrad.numLigne }" value="${blockTrad.ligneTraduction_1}" size="35"/>
                                    <c:if test="${!empty blockTrad.ligneOrigine_2}"><input class="form-control" type="text" name="line${ blockTrad.numLigne }" id="line${ blockTrad.numLigne }" value="${blockTrad.ligneTraduction_2}" size="35"/></c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </form>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>