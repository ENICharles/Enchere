<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link href="styles/UpdateStyle.css" rel="stylesheet">
   </head>
<body>
   
	<c:if test="${sessionScope.utilisateur == null}">
		<%@ include file="/WEB-INF/vues/fragments/entete.jspf"%>
	</c:if>
	<c:if test="${sessionScope.utilisateur != null}">
		<%@ include file="/WEB-INF/vues/fragments/enteteConnected.jspf"%>
	</c:if>
	
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