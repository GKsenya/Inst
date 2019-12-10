package InstagramV01.WorkClasses;

import InstagramV01.Interface.ResourceWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

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
    public void saveNewPost(UserPost userPost) throws FileNotFoundException {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        User user = userPost.getUser();
        Post post = userPost.getPosts().get(0);
        try {
            File file=new File(String.valueOf(userPost.getPosts().get(0).getImgIn()));
            FileInputStream fis=new FileInputStream(file);
            PreparedStatement ps=con.prepareStatement("INSERT INTO `Inst_post`(`user_id`, `img`, `comment`) VALUES (?,?,?)");
            ps.setInt(1,userPost.getUser().getId());
            ps.setBinaryStream(2,fis,(int)file.length());
            ps.setString(3, userPost.getPosts().get(0).getComment());
            ps.executeUpdate();
            ps.close();
            fis.close();
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