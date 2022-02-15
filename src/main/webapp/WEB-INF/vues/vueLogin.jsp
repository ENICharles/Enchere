<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<input type="text" name="identifiant" id="identifiant" value="identifiant">
	
		<!--  Création du champ password -->
		<label for="passWord">Mot de passe</label>
		<input type="text" name="password" id="password" value="password">
	

		<!--  Création du bouton connexion -->
		<input type="submit" value="Connexion">
		
		<!-- Recupérer le message d'erreur si champs id = null ou champs pswd = null  -->
		<c:if if(id == null || pswd == null)>


		</c:if>
				
	
	</form>
	

	
</body>
</html>