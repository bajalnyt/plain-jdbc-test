import java.sql.*;
import java.util.Properties;
 
/**
 * This program demonstrates how to make database connection with Oracle
 * database server.
 * @author www.codejava.net
 *
 */
public class Main {
 
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String dbURL2 = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+System.getenv("DB_HOST")+")(PORT=1522))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=ECST2)))";
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");
            long startTime = System.currentTimeMillis();

            conn = DriverManager.getConnection(dbURL2, username, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, instrument_id,start_date FROM subscription " +
                    "WHERE id In (1029779155,1029779163,1029779164,1029779151,1029779157,1029779159,1029779160,1029779169,1029779170)");

            while (rs.next()) {
                String lastName = rs.getString("instrument_id");
                System.out.print(lastName +",");
            }
            System.out.println("Query completed in");
            System.out.print(System.currentTimeMillis() - startTime);
            System.out.println(" milliseconds");

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
