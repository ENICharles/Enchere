<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link href="styles/ListeEncheresStyle.css" rel="stylesheet">
        
</head>
<body>



	<div class="container-fluid">
	<c:if test="${sessionScope.utilisateur == null}">
		<%@ include file="/WEB-INF/vues/fragments/entete.jspf"%>
	</c:if>
	<c:if test="${sessionScope.utilisateur != null}">
		<%@ include file="/WEB-INF/vues/fragments/enteteConnected.jspf"%>
	</c:if>
		
		<p>${requestScope.erreur}</p>
	
		<form action="accueil" method="post">
			<h2>Filtres :</h2>
	
			<select name="categorie" id="categorie">
				<option value="0">toutes</option>
				<c:forEach var="item" items="${requestScope.lstCategories}">
					<option value="${item.noCategorie}">${item.libelle}</option>
				</c:forEach>
			</select> 
			
			<input type="text" name="articleToFind" id="articleToFind" value="">
			<input class="bouton" type="submit" name="rechercher" value="rechercher">		
		</form>

		<div class="row">
			<c:forEach var="article" items="${requestScope.lstArticles}">
				<div class="col-sm-3">
					<div class="card">
						<div class="card-body">
								<img class="imgArt" src="data:image/jpeg;base64,${article.photo}" alt="photo de ${article.nomArticle}"><br>
								<h5 class="card-title">${article.nomArticle}</h5>
								<p class="card-text">${article.description}</p>
								<p class="card-text">Mise à prix : ${article.miseAPrix}p</p>
								<a href="DetailArticle?idArticle=${article.noArticle}" class="btn btn-primary">Détail de l'article</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>

