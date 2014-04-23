package gs.nick.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;
import org.junit.Test;

public class MySQLConnectionTest extends TestCase {

    @Test
    public void testConnect() {
        String host,name,username,password,answer;
        answer = "--fail--";
        
        // ENV
        host = System.getenv("DB_HOST");
        name = System.getenv("DB_NAME");
        username = System.getenv("DB_USER");
        password = System.getenv("DB_PASS");
        
        String query = "SELECT name FROM systems WHERE id=10;";
        String dbUrl = "jdbc:mysql://" + host + "/" + name;
        String dbClass = "com.mysql.jdbc.Driver";
        
        try {

            Class.forName(dbClass);
            Connection connection = DriverManager.getConnection(
                    dbUrl,
                    username,
                    password
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                answer = resultSet.getString(1);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals("Dreamcast", answer);
    }
}
