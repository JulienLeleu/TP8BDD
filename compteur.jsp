<!DOCTYPE html>
<html>
	<head>
		<title>Compteurs et Objets</title>
		<%@ page import="java.util.*" %>
		<%@ page session="true" %>
		<%@ page errorPage="erreur.jsp" %>
		<%@ page contentType="text/html; charset=UTF-8" %>
	</head>
	<body>
	<%! // déclaration de l’objet compteur
		public class Cpt
		{ 
			private int val=0;
			public String getVal() { 
				return "" + val; 
			}
		
			public void incr() {
				val++;
			}
		}
	%>
	<% 
		// initialisation du compteur global
		Cpt global=(Cpt)application.getAttribute("global");
		if (global==null) { 
			global=new Cpt();
			application.setAttribute("global",global);
		}
		global.incr();
		// initialisation du compteur local
		Cpt local=(Cpt)session.getAttribute("local");
		if (local==null) { local=new Cpt();
			session.setAttribute("local",local);
		}
		local.incr();
	%>
	<h1>
	Vous avez accédé <%= local.getVal() %> fois à cette page
	sur les <%= global.getVal() %> accés effectués.
	</h1>
	</body>
</html>
