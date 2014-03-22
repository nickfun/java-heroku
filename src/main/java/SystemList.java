import java.sql.*;

import com.google.gson.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class SystemList extends HttpServlet {

    private ArrayList<Object> doQuery() {
	String dbUrl = "jdbc:mysql://"+System.getenv("DB_HOST")+"/"+System.getenv("DB_NAME");
	String dbClass = "com.mysql.jdbc.Driver";
	String query = "select * from systems order by company, name;";
	try {
	    Class.forName(dbClass);
	    Connection conn = DriverManager.getConnect(dbUrl, System.getenv("DB_USER"), System.getenv("DB_PASS"));
	    Statement smt = conn.createStatement();
	    ResultSet sqlresults = smt.executeQuery(query);
    }

    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
	throws ServletException, IOException
    {
	

}
