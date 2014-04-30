package gs.nick.server;

import java.sql.*;
import java.util.ArrayList;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class SystemList extends HttpServlet {

    private ArrayList<String> doQuery() throws ClassNotFoundException, SQLException {
        System.out.println("SystemList::doQuery");
        String dbUrl = "jdbc:mysql://" + System.getenv("DB_HOST") + "/" + System.getenv("DB_NAME");
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
        while (sqlresults.next()) {
            systemNames.add(sqlresults.getString("company") + " " + sqlresults.getString("name"));
        }
        return systemNames;
    }

    private Connection getConnection() {
        Connection connection = null;
        String user, pass, url, answer;
        URI dbUri;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException ex) {
            return null;
        }

        user = dbUri.getUserInfo().split(":")[0];
        pass = dbUri.getUserInfo().split(":")[1];
        url = "jdbc:" + dbUri.getScheme() + "://" + dbUri.getHost() + dbUri.getPath();

        String query = "SELECT name FROM systems WHERE id=10;";
        String dbClass = "com.mysql.jdbc.Driver";

        try {
            Class.forName(dbClass);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException 
    {
        try {
            Connection conn = getConnection();
            if (conn == null) {
                resp.getWriter().println("DB FAIL");
            }
            ArrayList<String> systemNames = new ArrayList<>();
            resp.setContentType("text/html");
            resp.getWriter().print("Hello!");
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM systems;");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                systemNames.add( rs.getString("name") );
            }
            resp.getWriter().print( systemNames.toString() );
        } catch (SQLException ex) {
            Logger.getLogger(SystemList.class.getName()).log(Level.SEVERE, null, ex);
            resp.getWriter().print("DB Error");
            return;
        }
        
    }

}
