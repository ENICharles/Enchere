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
				<img src="data:image1/jpeg;base64,${sessionScope.article.photo}" alt="photo de ${sessionScope.article.nomArticle}" width="200px" height="200px">
			</article>

			<article>
				<em>Description de l'article</em><br>
				<form action="VendreArticle" id="Validation" method="post">
					<label>Article</label>
					<input type="text" name="nom" value="${sessionScope.article.nomArticle}" /><br>

					<label>Description</label> 
					<input name="description" type="text" value="${sessionScope.article.description}" required="required"/><br> 
					
					<label>Catégorie</label>
					<select name="categorie" id="categorie">
						<c:forEach var="item" items="${requestScope.lstCategories}">
							<c:if test="${sessionScope.article.categorie.libelle != item.libelle}">
								<option value="${item.noCategorie}">${item.libelle}</option>
							</c:if>
							<c:if test="${sessionScope.article.categorie.libelle == item.libelle}">
								<option value="${item.noCategorie}" selected>${item.libelle}</option>
							</c:if>
							<%-- <option value="${item.noCategorie}">${item.libelle}</option> --%>
						</c:forEach>
					</select><br>
					<label>Photo de l'article</label>
					<!-- <a href="../images/pc gamer.jpg" alt="photo pc gamer"> Cliquez ici</a><br> -->
					<input type="file" name="imgArticle" accept="image/png, image/jpeg"/><br>

					<label>Mise à prix</label>					
					<input type="number" name="miseaprix" required="required" min="1" value="${sessionScope.article.miseAPrix}"><br>
					
					<label>Début de l'enchère</label>
					<c:if test="${sessionScope.article != null}">
						<input type="date" name="dateDebut" required="required" value="${sessionScope.article.dateDebutEnchere}"><br>
					</c:if>
					<c:if test="${sessionScope.article == null}">
						<input type="date" name="dateDebut" required="required" value="${requestScope.dateD}"><br>
					</c:if>
					
					<label>Fin de l'enchère</label>	
					<c:if test="${sessionScope.article != null}">
						<input type="date" name="dateFin" required="required" value="${sessionScope.article.dateFinEnchere}"><br>
					</c:if>
					<c:if test="${sessionScope.article == null}">
						<input type="date" name="dateFin" required="required" value="${requestScope.dateF}"><br>
					</c:if>				
				
					<em>Lieu de retrait</em><br>
					<label>Rue</label> 
					<input type="text" name="rue" value="${utilisateur.rue}" required="required" /><br>

					<label>Code postal</label> 
					<input type="text" name="codepostal" value="${utilisateur.codePostal}" required="required" /><br>
					
					<label> Ville</label> 
					<input type="text" name="ville" value="${utilisateur.ville}" required="required"/><br>
					<input class="champ" name="action" type="submit" value="Enregistrer" /> 
					<input class="champ" name="action" type="submit" value="Annuler" />
					<input class="champ" name="action" type="submit" value="Annuler la vente" />
				</form>
				
				<br><br>
				<form method="post" action="UploadImage" enctype="multipart/form-data">
				  <input type="file" name="fic" accept="image/png, image/jpeg"/>
				  <input type="submit" value="Upload" />
				</form> 
			</article>
		</section>	
</div>
</body>
</html>