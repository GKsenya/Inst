package InstagramV01.WorkClasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUserSignIn {

    public static User userSignIn(String login, String password){
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `user_login` = '" + login
                    + "' AND `user_password` = '" + password + "'");
            rs.next();
            String name = rs.getString("user_name");
            int id = rs.getInt("id");
            dbConnector.closeConnection();
            return new User(login, name, id);
        }catch (Exception ex){
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("");
        }
        return null;
    }

}
