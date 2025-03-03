import java.sql.*;

public class DBWriter {
    private static String _conn, _user, _password;
    public static void Init(String connString, String user, String password){
        _conn = connString;
        _user = user;
        _password = password;
    }
    public static void AddToDB(String message, String ipAddr, String dt){
        if (_conn == "") {
            return;
        }
        String sql = "INSERT INTO eventlog(eventdt, eventtext, eventIP) VALUES(?,?,?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(_conn, _user, _password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dt );
            ps.setString(2, message);
            ps.setString(3, ipAddr);

            ps.addBatch();

            ps.executeBatch();
            conn.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
