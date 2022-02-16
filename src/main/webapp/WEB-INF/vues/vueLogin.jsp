<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchere - Login</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	
		<!--  Création du champ identifiant -->
		<form action="Controler" method="post">
	
		<label for="identifiant">Identifiant</label>
		<input type="text" name="identifiant" id="identifiant" value="">
	
		<!--  Création du champ password TODO : passer en type password-->
		<label for="passWord">Mot de passe</label>
		<input type="text" name="password" id="password" value="password">	

		<!--  Création du bouton connexion -->
		<input type="submit" value="Connexion">
		
		<!-- Recupérer le message d'erreur si champs id = null ou champs pswd = null  -->
		<!-- ajouter une mise en forme -->
		<p>${requestScope.erreur}</p>
	
	</form>
	

	
</body>
</html>