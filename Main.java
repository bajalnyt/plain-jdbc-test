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
        final String SQL = "INSERT INTO core_owner.google_real_time_developer_subscription_notifications (id, parent_notification_version, package_name,event_time_millis, subscription_notification_version,                                                                              notification_type, purchase_token,                                                                              subscription_id, order_id,                                                                              notification_json, created_by)VALUES (1,        'parentNotificationVersion',        'packageName',        12345667788,        'subscriptionNotificationVersion',        'notificationType',        'purchaseToken',        'subscriptionId',        'orderId',        'notificationJson',        'rf')";
        final int NUM_EXECUTIONS = 1;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String dbURL2 = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+System.getenv("DB_HOST")+")(PORT=1522))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=ECST2)))";
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");
            long startTime;

            conn = DriverManager.getConnection(dbURL2, username, password);
            List<Long> durations = new ArrayList<>();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            // for(int i= 0 ; i< NUM_EXECUTIONS; i++) {
            //     startTime = System.nanoTime();
            //     ResultSet rs = stmt.executeQuery(SQL);

            //     while (rs.next()) {
            //         String instr_id = rs.getString("instrument_id");
            //     }
            //     durations.add(System.nanoTime() - startTime);
            // }

            // long sum =  durations.stream().mapToLong(a -> a).sum();
            // long max = Collections.max(durations);
            // long min = Collections.min(durations);

            // System.out.println("Min :" + min +  ", Max :" + max + ", Avg :" + sum/NUM_EXECUTIONS +" (nanoseconds)");

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
