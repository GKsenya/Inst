package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceWriter;
import InstagramV01.WorkClasses.DBWriter;
import InstagramV01.WorkClasses.Post;
import InstagramV01.WorkClasses.User;
import InstagramV01.WorkClasses.UserPost;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String comment = (String) request.getAttribute("comment");
        Blob img = (Blob) request.getAttribute("img");
        Post post = new Post(img, comment);
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        User user = new User("CanellaOwl", "Eva", 1);
        UserPost up = new UserPost(user, posts);
        ResourceWriter rw = new DBWriter();
        rw.saveNewPost(up);
        response.sendRedirect(request.getContextPath() + "/list");

    }
}
