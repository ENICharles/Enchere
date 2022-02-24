<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<style>
		a
		{
		  color: #4f5050;
		  padding: 0 5px;/* pour un espace de 5+5px par exemple*/
		}
	</style>
	
<title>Enchere - Login</title>
  <header>
		<nav class="navbar navbar-light bg-light">
		  <a class="navbar-brand" href="login" ><img src="images/logo.JPG" width="90px" alt="logo du site"/></a>
		  <h1>Connexion</h1>
		  <form class="form-inline">

		  </form>
		</nav>
  </header>
</head>
<body>
	
	
	<!--  Création du champ identifiant -->
	<form action="login" method="post">
		<label for="identifiant">Identifiant</label>
		<input type="text" name="identifiant" id="identifiant" value="Foufou">
	
		<label for="passWord">Mot de passe</label>
		<input type="password" name="password" id="password" value="123456">	

		<!--  Création du bouton connexion -->
		<input type="submit" value="Connexion">
		
		<!-- Recupérer le message d'erreur si champs id = null ou champs pswd = null  -->
		<!-- ajouter une mise en forme -->
		<p>${requestScope.erreur}</p>
	</form>
	
		<!--  Création du bouton Création du compte -->
	
	<form action="creation" method="get">
		<input type="submit" value="Création du compte">
		<a href="login"><input type="button" value="Retour Accueil"></a>
	</form>
</body>
</html>