<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link href="styles/UpdateStyle.css" rel="stylesheet">
   </head>
<body>
   
  <header>
		<nav class="navbar navbar-light bg-light">
		  <a class="navbar-brand" href="accueil" ><img src="images/logo.JPG" width="90px" alt="logo du site"/></a>
		  <h1>Liste des enchères</h1>
		  <form class="form-inline">
		    <a href="login"> se déconnecter </a>
		  </form>
		</nav>
  </header>
	<p>${requestScope.erreur}</p>
       <div>
            <form action="UpdateProfile" class="info" method="post">
                <label>Pseudo :</label><input type="text" name="pseudo" value="${sessionScope.utilisateur.pseudo}"/><br>
                <label>Nom :</label><input type="text" name="nom"  value="${sessionScope.utilisateur.nom}"/><br>
                <label>Prénom :</label><input type="text" name="prenom"  value="${sessionScope.utilisateur.prenom}"/><br>
                <label>Email :</label><input type="text" value="${sessionScope.utilisateur.email}"/><br>
                <label>Télephone :</label><input type="text" value="${sessionScope.utilisateur.telephone}"/><br>
                <label>Rue :</label><input type="text" name="rue"  value="${sessionScope.utilisateur.rue}"/><br>
                <label>Code postal :</label><input type="text" value="${sessionScope.utilisateur.codePostal}"/><br>
                <label>Ville:</label><input type="text" name="ville"  value="${sessionScope.utilisateur.ville}"/><br>
                
                <input class="champ" type="submit" value="Modifier"/>
            </form>
        </div>
</body>
</html>