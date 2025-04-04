package multi.user.chatapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static multi.user.chatapp.utils.ConfigReader.getValue;
import static multi.user.chatapp.utils.KeyConstants.*;


public interface DBConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(getValue(DRIVER_NAME));
        Connection conn = DriverManager.getConnection(getValue(DB_URL), getValue(DB_USERID), getValue(DB_PWD));
        return conn;
    }
}
