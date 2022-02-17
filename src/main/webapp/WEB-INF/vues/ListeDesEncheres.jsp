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
<title>Enchere - ListeDesEncheres</title>
</head>
<body>
<h1>ENI-Enchères</h1>
<h2>Liste des enchères</h2>
	
<!--  Création du filtre de recherche -->
	<div>
		<form action="Controler" method="post">
			<label for="search">Filtres : </label>
			<input type="search" name="search" id="search" value="Le nom de l'article contient">	

<!--  Création du selecteur de catégorie -->
			<label for="categories">Catégories : </label>
			<input type="select" name="categories" id="categories" value="Toutes">
			<option>Cat 01</option>
			<option>Cat 02</option>
			<option>Cat 02</option>	<!--  ou liste catégorie dans BDD ??? -->
					
<!--  Création du bouton Rechercher -->
			<input type="submit" value="Rechercher">
			
<!-- Recupérer le message d'erreur si champs l'un des champs n'est pas conforme  -->
			<p>${requestScope.erreur}</p>	
				
		</form>
	</div>	
		
	
	<div>
		<ul>
			<li>
				<!-- Enchere/Article 01 -->
			</li>
			<li>
				<!-- Enchere/Article 02 -->
			</li>
			<li>
				<!-- Enchere/Article 03 -->
			</li>
			<li>
				<!-- Enchere/Article ...n -->
			</li>

		</ul>
	
	</div>
</body>
</html>