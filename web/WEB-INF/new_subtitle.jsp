<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 23/03/2019
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

        <form method="post" action="nouveau" enctype="multipart/form-data">
            <legend>Nouveau fichier</legend>

            <div class="row">
                <div class="form-group">
                    <label for="titre" class="col-lg-6">Titre du fichier : </label>
                    <input class="form-control col-lg-3" type="text" name="titre" id="titre">
                </div>
            </div>
            </br>
            <div class="row">
                <div class="form-group">
                    <label for="fichier" class="col-lg-6">Fichier à envoyer : </label>
                    <div class="col-lg-2"></div>
                    <div class="col-lg-4">
                        <input type="file" name="fichier" id="fichier">
                    </div>
                </div>
            </div>
            </br>
            <div class="row">
                <div class="form-group">
                    <label for="lang_origin" class="col-lg-6 control-label">Selectionner la langue d'origine </label>
                    <div class="col-lg-6">
                        <select id="lang_origin" name="lang_origin" class="form-control" >
                        <option value="1">Français</option>
                        <option value="2">Anglais</option>
                        <option value="3">Allemand</option>
                        <option value="4">Italien</option>
                        <option value="5">Espagnol</option>
                        <option value="6">Mandarin</option>
                    </select>
                    </div>
                </div>
            </div>
            </br>
            <div class="row">
                <div class="form-group">
                    <label for="lang_trad" class="col-lg-6 control-label">Selectionner la langue de traduction </label>
                    <div class="col-lg-6">
                        <select id="lang_trad" name="lang_trad" class="form-control" >
                            <option value="1">Français</option>
                            <option value="2">Anglais</option>
                            <option value="3">Allemand</option>
                            <option value="4">Italien</option>
                            <option value="5">Espagnol</option>
                            <option value="6">Mandarin</option>
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
