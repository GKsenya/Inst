package InstagramV01.Interface;

import InstagramV01.WorkClasses.User;
import InstagramV01.WorkClasses.UserPost;

import java.util.List;

public interface ResourceUserReader {

    List<UserPost> readAllPosts();

    List<UserPost> readPostsById(int id);

    User getUserById(int id);
}
