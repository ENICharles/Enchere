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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"rel="stylesheet"integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"crossorigin="anonymous">
<title>Enchere - MonProfil</title>
<style>
		a
		{
		  color: #4f5050;
		  padding: 0 5px;/* pour un espace de 5+5px par exemple*/
		}
		.info
		{
			text-align: center;
		}
		label
		{
			/* padding-left:30%; */
			/* float:left; */
			text-align: left;
			width:120px;
			pading:5px;
		}
		input
		{
			text-align: center;
			pading:5px;
			margin:5px;
		}
		
	.ann 
	{
		float: right;
		padding:6px 0 6px 0
		font:Bold 13px Arial;
		background:#d34836;
		color: black;
		width:90px;
		border-radius:2px;
		border:none;
		width: 1000px;
		height: 80px
    }
}
</style>
</head>
<header>
	<nav class="navbar navbar-light bg-light">
	
		<a class="navbar-brand" href="login"><img src="images/logo.JPG"
		
			width="90px" alt="logo du site" /></a>
		<h1>Liste des enchères</h1>
		<form class="form-inline">
			<a href="login"> s'inscrire/se connecter </a>
		</form>
	</nav>
</header>
<body>
	<div>
		<p>${requestScope.erreur}</p>

		<form class="info" action="creation" method="post">
			<h2 class="titre">Mon profil</h2>
			<label for="pseudo">Pseudo : </label> 
			<input type="text" name="pseudo" id="pseudo" value="Dodo" required="required"><br /> 
			
			<label for="nom">Nom : </label> 
			<input type="text" name="nom" id="nom" value="Duck" required="required"><br /> 
			
			<label for="prenom">Prénom :</label> 
			<input type="text" name="prenom" id="prenom" value="Donald" required="required"><br />

			<label for="email">Email : </label> 
			<input type="email" name="email" id="email" value="Duck.d@exemple.com" required="required"><br />

			<label for="telephone">Téléphone : </label>
			<input type="tel" name="telephone" id="telephone" value="2345698731" required="required"><br />

			<label for="rue">Rue : </label> 
			<input type="text" name="rue" id="rue" value="Les Ombrelles" required="required"><br /> 
			
			<label for="codePostal">Code postal : </label> 
			<input type="text" name="codePostal" id="codePostal" value="45123" required="required"><br /> 
			
			<label for="ville">Ville : </label> 
			<input type="text" name="ville" id="ville" value="Perpette" required="required"><br /> 
			
			<label for="passWord">Mot de passe : </label> 
			<input type="password" name="password" id="password" value="123" required="required"><br /> 
			
			<label for="confirmMdp">Mot de passe : </label> 
			<input type="password" name="confirmMdp" id="confirmMdp" value="123" required="required"><br /> 
			
			<input type="submit" name="btn" value="Créer">
			<a href="login"><input type="button" value="Annuler"></a>
		</form>	
	</div>
</body>
</html>