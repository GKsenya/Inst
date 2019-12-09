package InstagramV01.WorkClasses;

import InstagramV01.Interface.ResourceUserReader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUserReader implements ResourceUserReader {
    private List<UserPost> userPost = new ArrayList<>();

    @Override
    public List<UserPost> readAllPosts(){
        String req = "SELECT `Inst_users`.`id` as ui, `Inst_users`.`user_name` as un, `Inst_users`.`user_login` as ul, `Inst_post`.`img`, `Inst_post`.`comment`, `Inst_post`.`date` FROM `Inst_post` INNER JOIN `Inst_users` ON `Inst_users`.`user_name` = (SELECT `Inst_users`.`user_name` FROM `Inst_users` WHERE id = `Inst_post`.`user_id`)";
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(req);
            while (rs.next()) {
                User user = new User(rs.getString("ul"), rs.getString("un"), rs.getInt("ui"));
                Post post = new Post(rs.getString("date"), rs.getBytes("img"), rs.getString("comment"));
                List<Post> postList = new ArrayList<>();
                postList.add(post);
                this.userPost.add(new UserPost(user, postList));
            }
            dbConnector.closeConnection();
        }catch (Exception ex){
            System.out.println("Всё херня, давай по-новой!");
        }
        return this.userPost;
    }
    @Override
    public List<UserPost> readPostsById(int id){
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        List<Post> postList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `id` = " + id);
            rs1.next();
            User user = new User(rs1.getString("user_login"), rs1.getString("user_name"), rs1.getInt("id"));
            ResultSet rs = stmt.executeQuery("SELECT * FROM `Inst_post` WHERE `user_id` = " + id);
            while (rs.next()) {
                Post post = new Post(rs.getString("date"), rs.getBytes("img"), rs.getString("comment"));
                postList.add(post);
            }
            this.userPost.add(new UserPost(user, postList));
            dbConnector.closeConnection();
        }catch (Exception ex){
            System.out.println("Всё херня, давай по-новой!");
        }
        return this.userPost;
    }
}
