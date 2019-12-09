package InstagramV01.WorkClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String url = "jdbc:mysql://remotemysql.com:3306/uGTs1iK5gj";
    private static String username = "uGTs1iK5gj";
    private static String password = "x1pWjkeSTI";
    private Connection con;

    public Connection createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url, username, password);
                return this.con;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection
            () throws SQLException {
            this.con.close();
    }

}
