import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
 

public class Main {
 
    public static void main(String[] args) {
        Connection conn = null;
        final String SQL = "select sub_acct_num from aristo_owner.subs_subscriber";
        final int NUM_EXECUTIONS = System.getenv("NUM_EXECUTIONS")==null?10:Integer.parseInt(System.getenv("NUM_EXECUTIONS"));

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            //String dbURL2 = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+System.getenv("DB_HOST")+")(PORT=1522))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=ECST2)))";
            String dbURL2 = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+System.getenv("DB_HOST")+")(PORT=1522))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=ARSTODV1)))";
            
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");
            long startTime;

            conn = DriverManager.getConnection(dbURL2, username, password);
            List<Long> durations = new ArrayList<>();

            Statement stmt = conn.createStatement();
            for(int i= 0 ; i< NUM_EXECUTIONS; i++) {
                startTime = System.nanoTime();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    String instr_id = rs.getString("sub_acct_num");
                }
                durations.add(System.nanoTime() - startTime);
            }

            long sum =  durations.stream().mapToLong(a -> a).sum();
            long max = Collections.max(durations);
            long min = Collections.min(durations);

            System.out.println("Min :" + min +  ", Max :" + max + ", Avg :" + sum/NUM_EXECUTIONS +" (nanoseconds)");
            System.out.println("Min :" + min/1000000 +  ", Max :" + max/1000000 + ", Avg :" + sum/NUM_EXECUTIONS/1000000 +" (milliseconds)");

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
