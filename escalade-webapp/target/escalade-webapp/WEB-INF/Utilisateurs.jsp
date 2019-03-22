<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Utilisateurs</title>
</head>
<body>
	<form method="post" action=ServletUtilisateurs>
		<p>
			<label for="identifiant">Identifiant : </label>
			<input type="text" name="identifiant" id="identifiant" />
		</p>
		<p>
			<label for="password">Mot de passe : </label>
			<input type="text" name="password" id="password" />
		</p>
		<p>
			<label for="mail">Mail : </label>
			<input type="text" name="mail" id="mail" />
		</p>
		<p>
			<label for="nom">Nom : </label>
			<input type="text" name="nom" id="nom" />
		</p>
		<p>
			<label for="prenom">Prenom : </label>
			<input type="text" name="prenom" id="prenom" />
		</p>

		<input type="submit" value="Envoyer" />
	</form>

	<ul>
		<c:forEach var="utilisateur" items="${ utilisateurs }">
			<li>
				<c:out value="${ utilisateur.identifiant }" />
				<c:out value="${ utilisateur.password }" />
				<c:out value="${ utilisateur.mail }" />
				<c:out value="${ utilisateur.nom }" />
				<c:out value="${ utilisateur.prenom }" />
			</li>
		</c:forEach>
	</ul>
</body>
</html>