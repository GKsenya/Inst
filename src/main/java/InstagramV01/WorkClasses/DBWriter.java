package InstagramV01.WorkClasses;

import InstagramV01.Interface.ResourceWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class DBWriter implements ResourceWriter {

    private boolean userInDB = false;

    @Override
    public void saveNewUser(User user) {
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `user_login` = '" + user.getLogin()
                    + "' AND `user_name` = '" + user.getName() + "'");
            if (!rs.next()) {
                String request = "INSERT INTO `Inst_users`(`user_login`, `user_password`, `user_name`) VALUES ('"
                        + user.getLogin()
                        + "', '" + user.getPassword()
                        + "', '" + user.getName() + "')";
                stmt.execute(request);
            } else {
                this.userInDB = true;
            }
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
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


            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `inst_post` WHERE `user_id` = '" + user.getId()
                    + "' AND `img_path` = '" + post.getImg() + "' AND `comment` = '" + post.getComment() + "'");
            if (!rs.next()) {
                rs.close();
                PreparedStatement ps = con.prepareStatement("INSERT INTO `inst_post`(`user_id`, `img_path`, `img`, `comment`) VALUES (?, ?, ?, ?)");
                ps.setInt(1, user.getId());
                if(!post.getImg().equalsIgnoreCase("")){
                    File file = new File(post.getImg());
                    FileInputStream fis = new FileInputStream(file);
                    ps.setString(2, post.getImg());
                    ps.setBinaryStream(3, fis, (int)file.length());
                }else{
                    ps.setString(2, post.getImg());
                    ps.setBinaryStream(3, null, 0);
                }
                ps.setString(4, post.getComment());
                ps.executeUpdate();
                ps.close();
            }else{
                rs.close();
            }

        } catch (Exception ex) {
            System.out.println("cerf");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int saveLike(UserPost userPost){
        int likes = 0;
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        User user = userPost.getUser();
        Post post = userPost.getPosts().get(0);
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `likes` WHERE `post_id`=" + post.getId()
                    + " AND `user_id`=" + user.getId());
            if (!rs.next()) {
                String request = "INSERT INTO `likes`(`user_id`, `post_id`) VALUES ("
                        + user.getId()
                        + ", " + post.getId() + ")";
                stmt.execute(request);
            }else {
                String request = "DELETE FROM `likes` WHERE `user_id` = "
                        + user.getId()
                        + " AND `post_id` = " + post.getId();
                stmt.execute(request);
            }
            ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM `likes` WHERE `post_id`=" + post.getId());
            rs1.next();
            likes = rs1.getInt("count(*)");
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return likes;
    }

    @Override
    public void saveComment(UserPost userPost, String comment){
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        User user = userPost.getUser();
        Post post = userPost.getPosts().get(0);
        try {
            Statement stmt = con.createStatement();
            String request = "INSERT INTO `comments`(`user_id`, `post_id`, `comment`) VALUES ("
                    + user.getId()
                    + ", " + post.getId()
                    + ", '"+ comment +"')";
            stmt.execute(request);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Сууууууууууууукааааааааааааа");
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
