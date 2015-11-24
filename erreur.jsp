<!-- erreur.jsp -->
<%@ page isErrorPage="true" %>
<html>
	<head>
		<title>page d’affichage d’erreur</title>
	</head>
	<body>
		<center>
			<h3><%= exception.toString() %></h3>
		</center>
	</body>
</html>
