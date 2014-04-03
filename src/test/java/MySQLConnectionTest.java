
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

public class MySQLConnectionTest extends TestCase {

    public void testConnect() {
        String dbUrl = "jdbc:mysql://" + System.getenv("DB_HOST") + "/" + System.getenv("DB_NAME");
        String dbClass = "com.mysql.jdbc.Driver";
        String query = "SELECT  name FROM systems ORDER BY company asc;";
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        try {

            Class.forName(dbClass);
            Connection connection = DriverManager.getConnection(dbUrl,
                    username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String systemName = resultSet.getString(1);
                System.out.println("System name : " + systemName);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
