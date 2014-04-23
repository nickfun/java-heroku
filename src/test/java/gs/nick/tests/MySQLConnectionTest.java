package gs.nick.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;
import org.junit.Test;

public class MySQLConnectionTest extends TestCase {

    @Test
    public void testConnect() {
        System.out.println("testConnect");
        String user,pass,url,answer;
        URI dbUri;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException ex) {
            fail("Cant connect to database");
            return;
        }
        answer = "--fail--";
        
        user = dbUri.getUserInfo().split(":")[0];
        pass = dbUri.getUserInfo().split(":")[1];
        url = "jdbc:" + dbUri.getScheme() + "://" + dbUri.getHost() + dbUri.getPath();
        
        String query = "SELECT name FROM systems WHERE id=10;";
        String dbClass = "com.mysql.jdbc.Driver";

        
        try {

            Class.forName(dbClass);
            Connection connection = DriverManager.getConnection(url,user,pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                answer = resultSet.getString(1);
            }
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals("Dreamcast", answer);
    }
}
