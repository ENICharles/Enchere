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

	<script type="text/javascript">
		function activer() 
		{
			let inputElt = document.getElementById('action').disabled;
			
			document.getElementById('nom').disabled=!document.getElementById('nom').disabled;
			document.getElementById('description').disabled=!document.getElementById('description').disabled;
			document.getElementById('miseaprix').disabled=!document.getElementById('miseaprix').disabled;
			document.getElementById('dateDebut').disabled=!document.getElementById('dateDebut').disabled;
			document.getElementById('dateFin').disabled=!document.getElementById('dateFin').disabled;
			document.getElementById('rue').disabled=!document.getElementById('rue').disabled;
			document.getElementById('codepostal').disabled=!document.getElementById('codepostal').disabled;
			document.getElementById('ville').disabled=!document.getElementById('ville').disabled;
			document.getElementById('action').disabled=!document.getElementById('action').disabled;
		}
	</script>

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
				<!-- <img src="./images/pc gamer.jpg" alt="photo pc gamer" width="200px" height="200px"> -->
				<img src="data:image1/jpeg;base64,${sessionScope.article.photo}" alt="photo de ${sessionScope.article.nomArticle}" width="200px" height="200px"><br>
			</article>

			<article>
				<em>Description de l'article</em><br>
				<form action="VendreArticle" id="Validation" method="get">
					<label>Article</label>
					<input type="text" id="nom" name="nom" value="${sessionScope.article.nomArticle}" disabled/><br>

					<label>Description</label> 
					<input type="text" id="description" name="description" value="${sessionScope.article.description}" required="required" disabled/><br> 
					
					<label>Catégorie</label>					
					<input type="text" id="categorie" value="${sessionScope.article.categorie.libelle}" disabled/><br>
					
					<label>Mise à prix</label>					
					<input type="number" id="miseaprix" name="miseaprix" required="required" value="${sessionScope.article.miseAPrix}" disabled/><br>
					
					<label>Début de l'enchère</label>
					<input type="text" id="dateDebut" name="dateDebut" required="required" value="${sessionScope.article.dateDebutEnchere}" disabled/><br>
					
					<label>Fin de l'enchère</label>					
					<input type="text" id="dateFin" name="dateFin" required="required" value="${sessionScope.article.dateFinEnchere}" disabled/><br>
				
					<em>Lieu de retrait</em><br>
					<label>Rue</label> 
					<input type="text" id="rue" name="rue" value="${utilisateur.rue}" required="required"  disabled/><br>

					<label>Code postal</label> 
					<input type="text" id="codepostal" name="codepostal" value="${utilisateur.codePostal}" required="required"  disabled/><br>
					
					<label> Ville</label> 
					<input type="text" id="ville" name="ville" value="${utilisateur.ville}" required="required" disabled/><br>
					
					<input class="champ" id="action" name="action" type="submit" value="modifier"/>
					<!-- <input class="champ" onclick="activer()" value="Modifier" /> --> 

				</form>
			</article>
		
		

		</section>
</div>
</body>
</html>