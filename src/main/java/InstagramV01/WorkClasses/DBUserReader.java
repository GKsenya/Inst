package InstagramV01.WorkClasses;

import InstagramV01.Interface.ResourceUserReader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUserReader implements ResourceUserReader {

    @Override
    public List<UserPost> readAllPosts(){
        List<UserPost> userPost = new ArrayList<>();
        String req = "SELECT `Inst_users`.`id` as ui, `Inst_users`.`user_name` as un, `Inst_users`.`user_login` as ul, `Inst_post`.`imgPath`, `Inst_post`.`comment`, `Inst_post`.`date` FROM `Inst_post` INNER JOIN `Inst_users` ON `Inst_users`.`user_name` = (SELECT `Inst_users`.`user_name` FROM `Inst_users` WHERE id = `Inst_post`.`user_id`) ORDER BY `Inst_post`.`id` DESC";
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(req);
            while (rs.next()) {
                User user = new User(rs.getString("ul"), rs.getString("un"), rs.getInt("ui"));
                Post post = new Post(rs.getString("date").substring(0, rs.getString("date").lastIndexOf(" ")), rs.getString("imgPath"), rs.getString("comment"));
                List<Post> postList = new ArrayList<>();
                postList.add(post);
                userPost.add(new UserPost(user, postList));
            }
        }catch (Exception ex){
            return userPost;
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userPost;
    }
    @Override
    public UserPost readPostsById(int id){
        User user = null;
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        List<Post> postList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `id` = " + id);
            rs1.next();
            user = new User(rs1.getString("user_login"), rs1.getString("user_name"), rs1.getInt("id"));
            ResultSet rs = stmt.executeQuery("SELECT * FROM `Inst_post` WHERE `user_id` = " + id);
            while (rs.next()) {
                Post post = new Post(rs.getString("date"), rs.getString("imgPath"), rs.getString("comment"));
                postList.add(post);
            }
        }catch (Exception ex){
            postList = null;
        }finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }
        return new UserPost(user, postList);
    }
    @Override
    public User getUserById(int id){
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        User user;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `id` = " + id);
            rs1.next();
            user = new User(rs1.getString("user_login"), rs1.getString("user_name"), rs1.getInt("id"));
        }catch (Exception ex){
            user = null;
        }
        return user;
    }
}
