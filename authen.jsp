<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="javax.servlet.*" %>
<%@ page session="true" %>
<%@ page errorPage="erreur.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String url = "jdbc:postgresql://psqlserv/da2i";
	String nom = "leleuj";
	String mdp = "moi";
	Connection con=null;

	String login = request.getParameter("login");
	String password = request.getParameter("password");
	
	Class.forName("org.postgresql.Driver");
	
	con = DriverManager.getConnection(url,nom,mdp);
	Statement stmt = con.createStatement();
	
	stmt.executeUpdate("CREATE TABLE IF NOT EXISTS logs (login TEXT, password TEXT, dateDeConnexion TIMESTAMP, adresseIp TEXT, CONSTRAINT pk_login PRIMARY KEY (login));");
	String query = "SELECT * FROM logs WHERE login='" + login + "' AND password='" + password + "';";
	ResultSet rs = stmt.executeQuery(query);
	if (rs.next()) {
		query = "UPDATE logs SET dateDeConnexion='" + new Date() + "', adresseIp='" + request.getRemoteAddr() +"' WHERE login='" + login + "';";
		stmt.executeUpdate(query);
		out.println("<h1>Bienvenue " + login + " ! </h1>");
		out.println("<h1>Votre mdp : " + password + " ! </h1>");
		out.println("<h1>Votre dernière connexion : " + rs.getDate("dateDeConnexion") + "</h1>");
		out.println("<h1>Votre dernière adresse IP : " + rs.getString("adresseIp") + "</h1>");
	}
	else {
		query = "INSERT INTO logs VALUES ('" + login + "', '" + password + "', '" + new Date() + "', '" + request.getRemoteAddr()+"');";
		stmt.executeUpdate(query);
		out.println("<h1>Bienvenue " + login + " !</h1>");
	}
	con.close();
%>
