import java.sql.*;
import java.util.ArrayList;

import com.google.gson.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class SystemList extends HttpServlet {

    private ArrayList<String> doQuery() throws ClassNotFoundException, SQLException {
	System.out.println("SystemList::doQuery");
	String dbUrl = "jdbc:mysql://"+System.getenv("DB_HOST")+"/"+System.getenv("DB_NAME");
	String dbClass = "com.mysql.jdbc.Driver";
	String query = "select * from systems order by company, name;";
	
	Class.forName(dbClass);
	Connection conn = DriverManager.getConnection(
						   dbUrl, 
						   System.getenv("DB_USER"), 
						   System.getenv("DB_PASS")
	);
	System.out.println("Conn is established");
	Statement smt = conn.createStatement();
	System.out.println("Statement created");
	ResultSet sqlresults = smt.executeQuery(query);
	System.out.println("SystemList, executQuery finished");
	ArrayList<String> systemNames = new ArrayList<String>();
	while( sqlresults.next() ) {
	    systemNames.add( sqlresults.getString("company") + " " + sqlresults.getString("name") );
	}
	return systemNames;
    }    

    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
	throws ServletException, IOException 
    {
	ArrayList<String> gameNames;
	resp.setContentType("text/html");
	resp.getWriter().print("Hello!");
	try {
	    gameNames = doQuery();
	} catch( ClassNotFoundException e ) {
	    gameNames = new ArrayList<String>();
	    gameNames.add("Query failed");
	} catch( SQLException e ) {
	    gameNames = new ArrayList<String>();
	    gameNames.add("SQL Error " + e.getErrorCode());
	    gameNames.add("msg: " + e.getMessage());
	}
	for( String name : gameNames ) {
	    resp.getWriter().print("<br>" + name);
	}
    }

}
