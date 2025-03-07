import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class DBWriter {
    private static String _conn, _user, _password;

    public static void Init(String dbCredsPath){
        try (Scanner scanner = new Scanner(new File(dbCredsPath))) {
            _conn = scanner.nextLine();
            _user = scanner.nextLine();
            _password = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void AddToDB(String message, String ipAddr, String dt)  {
        if (_conn.equals("")) {
            return;
        }

        String sql = "INSERT INTO eventlog(eventdt, eventtext, eventIP) VALUES(?,?,?)";
        System.out.println("CLASSPATH: " + System.getProperty("java.class.path"));
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(_conn, _user, _password);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dt );
            if(message.length() > 255){
                message = message.substring(1,255);
            }
            ps.setString(2, message);
            ps.setString(3, ipAddr);

            ps.addBatch();

            ps.executeBatch();
            conn.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
