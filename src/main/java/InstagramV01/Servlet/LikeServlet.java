package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceUserReader;
import InstagramV01.Interface.ResourceWriter;
import InstagramV01.WorkClasses.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LikeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = (Integer) req.getSession().getAttribute("id");
        ResourceUserReader rur = new DBUserReader();
        int postID = Integer.parseInt(req.getParameter("postId"));

        User user = rur.getUserById(id);
        Post post = rur.getPostById(postID);
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        ResourceWriter dbWriter = new DBWriter();
        dbWriter.saveLike(new UserPost(user, posts));

        resp.sendRedirect(req.getContextPath() + "/posts");
    }
}
