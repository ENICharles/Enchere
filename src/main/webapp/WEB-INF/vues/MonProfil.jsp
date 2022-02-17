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
<title>Enchere - MonProfil</title>
</head>
<body>
<h1>ENI-Enchères</h1>
<h2>Mon profil</h2>
	<div>
<!--  Création des champs du profil -->
		<form action="CreaCompteControler" method="post">
			<ul>
				<li>	
					<label for="pseudo">Pseudo : </label>
					<input type="text" name="pseudo" id="pseudo" value="rrr">
				</li>
				<li>	
					<label for="nom">Nom : </label>
					<input type="text" name="nom" id="nom" value="rrr">
				</li>
				<li>	
					<label for="prenom">Prénom : </label>
					<input type="text" name="prenom" id="prenom" value="rrr">	
				</li>
				<li>	
					<label for="email">Email : </label>
					<input type="email" name="email" id="email" value="exemple@exemple.com">
				</li>
				<li>		
					<label for="telephone">Téléphone : </label>
					<input type="tel" name="telephone" id="telephone" value="555">	
				</li>
				<li>	
					<label for="rue">Rue : </label>
					<input type="text" name="rue" id="rue" value="rrr">	
				</li>
				<li>
					<label for="codePostal">Code postal : </label>
					<input type="text" name="codePostal" id="codePostal" value="555">	
				</li>
				<li>	
					<label for="ville">Ville : </label>
					<input type="text" name="ville" id="ville" value="rrr">	
				</li>
			</ul>

<!-- Saisie du mot de passe et Confirmation -->
			<ul>	
				<li>
					<label for="passWord">Mot de passe : </label>
					<input type="password" name="password" id="password" value="xxx">
				</li>
				<li>	
					<label for="confirmMdp">Mot de passe : </label>
					<input type="text" name="confirmMdp" id="confirmMdp" value="xxx">
				</li>
			</ul>
			
<!--  Création du bouton "Créer" -->
			<input type="submit" value="Créer">
		</form>	
<!-- Si validation du formulaire => Redirection sur la page Accueil (Liste des enchères)  -->
<!-- Sinon : Recupérer le message d'erreur si champs l'un des champs n'est pas conforme  -->
		<p>${requestScope.erreur}</p>

<!--  Création du bouton "Annuler" => Redirection sur la page d'accueil -->
		<form action="Controler" method="post">
				<input type="button" value="Annuler">
				<a href="">url de la page d'accueil</a>
		</form>	
	</div>
</body>
</html>