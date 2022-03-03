<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="./styles/VendreArticle.css" />

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
		<section class="descriptif">
			<article>
				<em>Photo de l'article</em> 
				<img src="./images/pc gamer.jpg"
					alt="photo pc gamer" width="200px" height="200px">
			</article>

			<article>
				<em>Description de l'article</em><br>
				<form action="MesEncheres" id="Validation" method="post">
					<label>Article</label>
					<input type="text" name="nom" value="${requestScope.article.nomArticle}" disabled="disabled"/><br>

					<label>Description</label> 
					<input name="description" type="text" value="${requestScope.article.description}" required="required" disabled="disabled"/><br> 
					
					<label>Catégorie</label>
					<input type="text" value="${requestScope.article.categorie.libelle}" disabled="disabled"/><br>
					
					<label>Mise à prix</label>					
					<input type="number" name="miseaprix" required="required" value="${requestScope.article.miseAPrix}" disabled="disabled"><br>
					
					<label>Début de l'enchère</label>
					<input type="text" name="dateDebut" required="required" value="${requestScope.article.dateDebutEnchere}" disabled="disabled"><br>
					
					<label>Fin de l'enchère</label>					
					<input type="text" name="dateFin" required="required" value="${requestScope.article.dateFinEnchere}" disabled="disabled">
				
				<em>Lieu de retrait</em><br>
					<label>Rue</label> 
					<input type="text" name="rue" value="${utilisateur.rue}" required="required"  disabled="disabled"/><br>

					<label>Code postal</label> 
					<input type="text" name="codepostal" value="${utilisateur.codePostal}" required="required"  disabled="disabled"/><br>
					
					<label> Ville</label> 
					<input type="text" name="ville" value="${utilisateur.ville}" required="required" disabled="disabled"/><br>
					
					<input class="champ" name="action" type="submit" value="Enregistrer" /> 

				</form>
			</article>
		
		

		</section>
</div>
</body>
</html>