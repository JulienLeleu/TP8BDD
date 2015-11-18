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

@WebServlet("/servlet/Insert")
public class Insert extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res ) 
       throws ServletException, IOException
  {
    PrintWriter out = res.getWriter();
    
    res.setContentType( "text/html" );
	
	Integer pno = (Integer)Integer.parseInt(req.getParameter("pno"));
	String name = req.getParameter("nom");
	String prenom = req.getParameter("prenom");
	String adresse = req.getParameter("adresse");
	
    //out.println("pno = '" + pno + "' nom = '"  + name + "' prenom = '"  + prenom + "' adresse = '" + adresse + "'");
    
    String url = "jdbc:postgresql://psqlserv/da2i";
	String nom = "leleuj";
	String mdp = "moi";
	Connection con=null;
	
	String query = "INSERT INTO pilote(pno, nom, prenom, adresse) VALUES('"+ pno + "', '" + nom + "', '" + prenom + "', '" + adresse + "');";
	out.println(query);
	try {
		//Enregistrement du driver
		Class.forName("org.postgresql.Driver");

		//Connexion à la base
		con = DriverManager.getConnection(url,nom,mdp);
		//out.println("Connexion établie avec succés");

		//Execution de la requete
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
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
