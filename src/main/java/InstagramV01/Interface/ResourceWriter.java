package InstagramV01.Interface;

import InstagramV01.WorkClasses.User;
import InstagramV01.WorkClasses.UserPost;

import java.io.FileNotFoundException;

public interface ResourceWriter {
    void saveNewUser(User user);

    boolean isUserInDB();

    void saveNewPost(UserPost userPost) throws FileNotFoundException;

    int saveLike(UserPost userPost);

    void saveComment(UserPost userPost, String comment);
}
