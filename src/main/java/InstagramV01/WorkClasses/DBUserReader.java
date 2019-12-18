package InstagramV01.WorkClasses;

import InstagramV01.Interface.ResourceUserReader;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUserReader implements ResourceUserReader {

    @Override
    public List<UserPost> readAllPosts(){
        List<UserPost> userPost = new ArrayList<>();
        Map<Integer,List<Comment>> comments = new HashMap<>();
        String req = "SELECT `Inst_users`.`id` as ui, `Inst_users`.`user_name` as un, `Inst_users`.`user_login` as ul, `inst_post`.`img_path`, `inst_post`.`img`,`inst_post`.`comment`, `inst_post`.`date`, `inst_post`.`id`, (select count(*) FROM `likes` WHERE `post_id` = `inst_post`.`id`) as likes FROM `inst_post` INNER JOIN `Inst_users` ON `Inst_users`.`id` = (SELECT `Inst_users`.`id` FROM `Inst_users` WHERE id = `inst_post`.`user_id`) ORDER BY `inst_post`.`id` DESC";
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            comments = this.getComments(con);
            ResultSet rs = stmt.executeQuery(req);
            while (rs.next()) {
                if(rs.getBinaryStream("img")!=null){
                    File image = new File(rs.getString("img_path"));
                    fileSaving(rs, image);
                }
                User user = new User(rs.getString("ul"), rs.getString("un"), rs.getInt("ui"));
                Post post = new Post(rs.getString("date").substring(0, rs.getString("date").lastIndexOf(" ")), rs.getString("img_path"), rs.getString("comment"), rs.getInt("id"), rs.getString("likes"), comments.get(rs.getInt("id")));
                List<Post> postList = new ArrayList<>();
                postList.add(post);
                userPost.add(new UserPost(user, postList));
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
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
        Map<Integer,List<Comment>> comments = new HashMap<>();
        try {
            comments = this.getComments(con);
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `id` = " + id);
            rs1.next();
            user = new User(rs1.getString("user_login"), rs1.getString("user_name"), rs1.getInt("id"));
            rs1.close();
            ResultSet rs = stmt.executeQuery("SELECT `inst_post`.`img_path`, `inst_post`.`img_path`, `inst_post`.`comment`, `inst_post`.`date`, `inst_post`.`id`, (select count(*) FROM `likes` WHERE `post_id` = `inst_post`.`id`) as likes FROM `inst_post` WHERE `user_id` = " + id);
            while (rs.next()) {
                if(rs.getBinaryStream("img")!=null){
                    File image = new File(rs.getString("img_path"));
                    fileSaving(rs, image);
                }
                Post post = new Post(rs.getString("date").substring(0, rs.getString("date").lastIndexOf(" ")), rs.getString("img_path"), rs.getString("comment"), rs.getInt("id"), rs.getString("likes"), comments.get(rs.getInt("id")));
                postList.add(post);
            }
            rs.close();
            stmt.close();
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
        List<Integer> likes = new ArrayList<>();
        User user;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `post_id` FROM `likes` WHERE `user_id` = " + id);
            while (rs.next()) {
                likes.add(rs.getInt("post_id"));
            }
            rs.close();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM `Inst_users` WHERE `id` = " + id);
            rs1.next();
            user = new User(rs1.getString("user_login"), rs1.getString("user_name"), rs1.getInt("id"), likes);
            rs1.close();
            stmt.close();
        }catch (Exception ex){
            user = null;
        }

        return user;
    }

    @Override
    public Post getPostById(int id){
        Map<Integer,List<Comment>> comments = new HashMap<>();
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        Post post;
        try {
            comments = this.getComments(con);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `inst_post`.`img_path`, `inst_post`.`img`, `inst_post`.`comment`, `inst_post`.`date`, `inst_post`.`id`, (select count(*) FROM `likes` WHERE `post_id` = `inst_post`.`id`) as likes FROM `inst_post` WHERE `id` = " + id);
            rs.next();
            if(rs.getBinaryStream("img")!=null){
                File image = new File(rs.getString("img_path"));
                fileSaving(rs, image);
            }
            post = new Post(rs.getString("date").substring(0, rs.getString("date").lastIndexOf(" ")), rs.getString("img_path"), rs.getString("comment"), rs.getInt("id"), rs.getString("likes"), comments.get(rs.getInt("id")));
            rs.close();
            stmt.close();
        } catch (Exception ex){
            post = null;
        }
        return post;
    }

    public int countLike(int id){
        int likes = 0;
        DBConnector dbConnector = new DBConnector();
        Connection con = dbConnector.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT count(*) as c FROM `likes` WHERE `post_id`=" + id);
            rs1.next();
            likes = rs1.getInt("c");
            rs1.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return likes;
    }

    private Map<Integer, List<Comment>> getComments(Connection con){
        Map<Integer,List<Comment>> comments = new HashMap<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT `Inst_users`.`id` as ui, `Inst_users`.`user_name` as un, `Inst_users`.`user_login` as ul, `comments`.`id`, `comments`.`user_id`, `comments`.`post_id`, `comments`.`comment`, `comments`.`date`FROM `comments` INNER JOIN `Inst_users` ON `Inst_users`.`id` = (SELECT `Inst_users`.`id` FROM `Inst_users` WHERE  `id` = `comments`.`user_id`)");
            while (rs1.next()) {
                User user = new User(rs1.getString("ul"), rs1.getString("un"), rs1.getInt("ui"));
                Comment comment = new Comment(user, rs1.getString("date").substring(0, rs1.getString("date").lastIndexOf(" ")), rs1.getString("comment"), rs1.getInt("post_id"));
                if (comments.containsKey(comment.getPostId())) {
                    List<Comment> newComments = comments.get(comment.getPostId());
                    newComments.add(comment);
                    comments.put(comment.getPostId(), newComments);
                } else {
                    List<Comment> newComments = new ArrayList<>();
                    newComments.add(comment);
                    comments.put(comment.getPostId(), newComments);
                }
            }
            rs1.close();
            stmt.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return comments;
    }


    private void fileSaving(ResultSet rs, File image) throws IOException, SQLException {
        if (!image.exists()){
            FileOutputStream fos = new FileOutputStream(image);
            byte[] buffer = new byte[1];
            InputStream is = rs.getBinaryStream("img");
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }
            fos.close();
        }
    }
}
