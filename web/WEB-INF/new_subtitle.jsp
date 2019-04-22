<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 23/03/2019
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nouveau sous-titre</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css"> <%@include file="css/editeur.css" %> </style>
</head>
<body>

<%@ include file="menu.jsp" %>

<div class="container">
    <div class="col-lg-2">
    </div>
    <div class="col-lg-8 border">

        <form method="post" action="/Traducteur_sous_titre/nouveau" enctype="multipart/form-data">
            <legend>Nouveau fichier</legend>

            <div class="row">
                <div class="form-group">
                    <label for="id_titre" class="col-lg-6">Titre du fichier : </label>
                    <input class="form-control col-lg-3" type="text" name="titre" value="${titre}" id="id_titre">
                </div>
            </div>
            </br>
            <div class="row">
                <div class="form-group">
                    <label for="fichier" class="col-lg-6">Fichier à envoyer : </label>
                    <div class="col-lg-2"></div>
                    <div class="col-lg-4">
                        <input type="file" name="fichier" value="${fichier}" id="fichier">
                    </div>
                </div>
            </div>
            </br>
            <div class="row">
                <div class="form-group">
                    <label for="id_lang_origin" class="col-lg-6 control-label">Selectionner la langue d'origine </label>
                    <div class="col-lg-6">
                        <select id="id_lang_origin" name="lang_origin" class="form-control" >
                            <option value="empty"></option>
                            <c:forEach items="${listeLangues}" var="language1" varStatus="numchamp">
                                <c:choose>
                                    <c:when test="${language1.langue eq lang_origin}">
                                        <option selected value="${language1.langue}">${language1.langue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${language1.langue}">${language1.langue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            </br>
            <div class="row">
                <div class="form-group">
                    <label for="id_lang_trad" class="col-lg-6 control-label">Selectionner la langue de traduction </label>
                    <div class="col-lg-6">
                        <select id="id_lang_trad" name="lang_trad" class="form-control" >
                            <option value="empty"></option>

                            <c:forEach items="${listeLangues}" var="language2" varStatus="numchamp">
                                <c:choose>
                                    <c:when test="${language2.langue eq lang_trad}">
                                        <option selected value="${language2.langue}">${language2.langue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${language2.langue}">${language2.langue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>>
                        </select>
                    </div>
                </div>
            </div>
            </br>
            <div class="row">
                <div class="col-lg-5"></div>
                <div class="col-lg-2">
                <input class="btn" type="submit" value="Créer" action="NewSubtitle">
                </div>
                <div class="col-lg-5"></div>
            </div>

        </form>

        <div class="col-lg-2">
        </div>
    </div>
</div>
</body>
</html>
