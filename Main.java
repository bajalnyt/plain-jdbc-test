import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        final String SQL = "SELECT ID, instrument_id,start_date FROM subscription " +
                           "WHERE id In (1029779155,1029779163,1029779164,1029779151," +
                           "1029779157,1029779159,1029779160,1029779169,1029779170)";
        final int NUM_EXECUTIONS = 1000;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String dbURL2 = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+System.getenv("DB_HOST")+")(PORT=1522))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=ECST2)))";
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");
            long startTime;

            conn = DriverManager.getConnection(dbURL2, username, password);
            List<Long> durations = new ArrayList<>();

            Statement stmt = conn.createStatement();
            for(int i= 0 ; i< NUM_EXECUTIONS; i++) {
                startTime = System.currentTimeMillis();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    String instr_id = rs.getString("instrument_id");
                }
                durations.add(System.currentTimeMillis() - startTime);
            }

            long sum =  durations.stream().mapToLong(a -> a).sum();
            long max = Collections.max(durations);
            long min = Collections.min(durations);

            System.out.println("Min :" + min +  ", Max :" + max + ", Avg :" + sum/NUM_EXECUTIONS +" milliseconds");

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
