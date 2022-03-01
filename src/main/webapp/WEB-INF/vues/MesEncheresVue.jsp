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
<style>
a {
	color: #4f5050;
	padding: 0 5px; /* pour un espace de 5+5px par exemple*/
}
</style>
</head>
<body>
	<div class="container">
		<header>
			<nav class="navbar navbar-light bg-light">
				<a class="navbar-brand" href="accueil"><img
					src="images/logo.JPG" width="90px" alt="logo du site" /></a>
				<h1>Liste des enchères et des articles de ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h1>
				
				<form class="form-inline">
					<a href="UpdateProfile"> Mon profil </a>
					<a href="accueil"> Vendre un article </a>
					<a href="login"> Déconnecter </a>
				</form>
			</nav>
		</header>

		<p>${requestScope.erreur}</p>

		<form action="MesEncheres" method=post>
			<h2>Filtres :</h2>

			<select name="categorie" id="categorie">
				<option value="0">toutes</option>
				<c:forEach var="item" items="${requestScope.lstCategories}">
					<option value="${item.noCategorie}">${item.libelle}</option>
				</c:forEach>
			</select>
			<div>
				<input type="radio" id="My" name="userFiltre" value="${sessionScope.utilisateur.noUtilisateur}"
					value="${sessionScope.utilisateur.noUtilisateur}" checked> <label
					for="My">Mes articles</label>
			</div>
			<div>
				<input type="radio" id="all" name="userFiltre" value="0"> <label
					for="all">Toute les articles</label>
			</div>

			<input type="text" name="articleToFind" id="articleToFind" value="">

			<c:set var="numUser" value="${sessionScope.utilisateur.noUtilisateur}" scope="request" />

			<input class="bouton" type="submit" name="rechercher"
				value="rechercher">
		</form>

		<div class="row">
			<c:forEach var="article" items="${requestScope.lstArticles}">
				<div class="col-sm-3">
					<div class="card">
						<div class="card-body">
							<img src="${pageContext.request.contextPath}/images/${article.idPossesseur}-${article.noArticle}.jpg"alt="photo pc gamer">
							<h5 class="card-title">${article.nomArticle}</h5>
							<p class="card-text">${article.description}</p>
							<p class="card-text">Prix : 100p</p>
							<a href="DetailArticle?idArticle=${article.noArticle}" class="btn btn-primary">Détail de l'article</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>

