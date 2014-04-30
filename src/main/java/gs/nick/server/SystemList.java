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

public class SystemList extends HttpServlet {

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
            resp.setContentType("application/json");
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM systems;");
            ResultSet rs = stm.executeQuery();
	    String company, name;
            while (rs.next()) {
                //systemNames.add( rs.getString("name") );
		company = rs.getString("company");
		name = rs.getString("name");
		systemNames.add( company + " " + name );
            }
            //resp.getWriter().print( systemNames.toString() + "<hr>" );
            Gson json = new Gson();
            resp.getWriter().print( json.toJson(systemNames) );
	    System.out.println("/systems - I served a list of systems");
        } catch (SQLException ex) {
            Logger.getLogger(SystemList.class.getName()).log(Level.SEVERE, null, ex);
            resp.getWriter().print("DB Error");
            return;
        }
        
    }

}
