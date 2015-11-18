// Servlet Test.java  de test de la configuration
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Insert2")
public class Insert2 extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res ) 
       throws ServletException, IOException
  {
    PrintWriter out = res.getWriter();
    
    res.setContentType( "text/html" );
    
    String url = "jdbc:postgresql://psqlserv/da2i";
	String nom = "leleuj";
	String mdp = "moi";
	Connection con=null;
	
	String table = req.getParameter("table");
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
		
		query = "INSERT INTO " + table + " VALUES (";
		for (int i = 1; i < nbColumn; i++){
			query += "'" + req.getParameter(rsmd.getColumnName(i)) + "', ";
		}
		query += "'" + req.getParameter(rsmd.getColumnName(nbColumn)) + "');";
		out.println(query);
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
