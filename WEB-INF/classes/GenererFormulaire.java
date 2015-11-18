// Servlet Test.java  de test de la configuration
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

@WebServlet("/servlet/GenererFormulaire")
public class GenererFormulaire extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res ) 
       throws ServletException, IOException
  {
    PrintWriter out = res.getWriter();
    
    res.setContentType( "text/html" );
	
	String table = req.getParameter("table");
    
    String url = "jdbc:postgresql://psqlserv/da2i";
	String nom = "leleuj";
	String mdp = "moi";
	Connection con=null;
	
	String query = "SELECT * FROM " + table + ";";
	//out.println(query);
	try {
		//Enregistrement du driver
		Class.forName("org.postgresql.Driver");

		//Connexion à la base
		con = DriverManager.getConnection(url,nom,mdp);
		//out.println("Connexion établie avec succés");

		//Execution de la requete
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int nbColumn = rsmd.getColumnCount();
		out.println("<h1>Table : " + table + "</h1>");
		out.println("<FORM action=\"Insert2\">");
		out.println("<input type=\"hidden\" name=\"table\" value=\"" + table + "\"/>");
		for (int i = 1; i <= nbColumn; i++){
			out.println("<p>" + rsmd.getColumnName(i) + ": <input type=\"TEXT\" name=\"" + rsmd.getColumnName(i) + "\"/></p>");
		}
		out.println("<input type=\"submit\"/ value=\"Insérer\">");
		out.println("</FORM>");
		
	} catch (Exception e) {
		out.println(e);
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
		}
		catch (Exception e) {
			out.println(e);
			e.printStackTrace();
		}
	}
  }
}
