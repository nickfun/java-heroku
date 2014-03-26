import java.sql.*;
import java.util.ArrayList;

import com.google.gson.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class SystemList extends HttpServlet {

    private ArrayList<String> doQuery() {
	String dbUrl = "jdbc:mysql://"+System.getenv("DB_HOST")+"/"+System.getenv("DB_NAME");
	String dbClass = "com.mysql.jdbc.Driver";
	String query = "select * from systems order by company, name;";
	
	Class.forName(dbClass);
	Connection conn = DriverManager.getConnect(dbUrl, System.getenv("DB_USER"), System.getenv("DB_PASS"));
	Statement smt = conn.createStatement();
	ResultSet sqlresults = smt.executeQuery(query);
	ArrayList<String> systemNames = new ArrayList<String>();
	while( sqlresults.next() ) {
	    systemNames.push( sqlresults.getString("company") + " " + sqlresults.getString("name") );
	}
	return systemNames;
    }    

    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
	throws ServletException, IOException
    {
	resp.getWriter().print("Hello!");
    }

}
