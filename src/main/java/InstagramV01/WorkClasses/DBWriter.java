package InstagramV01.WorkClasses;

import InstagramV01.Interface.ResourceWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter implements ResourceWriter {

    private boolean userInDB = false;
    @Override
    public void saveNewUser(User user){
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `user_login` = '" + user.getLogin()
                    + "' AND `user_name` = '" + user.getName() + "'");
            if(!rs.next()) {
                String request = "INSERT INTO `Inst_users`(`user_login`, `user_password`, `user_name`) VALUES ('"
                        + user.getLogin()
                        + "', '" + user.getPassword()
                        + "', '" + user.getName() + "')";
                stmt.execute(request);
            } else{
                this.userInDB = true;
            }
        }catch (Exception ex){
            System.out.println("Всё херня, давай по-новой!");
        }finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isUserInDB() {
        return userInDB;
    }

    @Override
    public void saveNewPost(UserPost userPost){
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        User user = userPost.getUser();
        Post post = userPost.getPosts().get(0);
        try {
            Statement stmt = con.createStatement();
            String request = "INSERT INTO `Inst_post`(`user_id`, `img`, `comment`) VALUES ('"
                        + user.getId()
                        + "', '" + post.getImgIn()
                        + "', '" + post.getComment() + "')";
             stmt.execute(request);
        }catch (Exception ex){
            System.out.println("Всё херня, давай по-новой!");
        }finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
