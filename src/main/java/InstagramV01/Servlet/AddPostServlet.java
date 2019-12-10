package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceUserReader;
import InstagramV01.Interface.ResourceWriter;
import InstagramV01.WorkClasses.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ResourceUserReader rur = new DBUserReader();
        User user = rur.getUserById(id);
        request.setAttribute("user", user);

        String comment = request.getParameter("comment");
        Post post = new Post(request.getParameter("img"), comment);
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        UserPost up = new UserPost(user, posts);

        ResourceWriter rw = new DBWriter();
        rw.saveNewPost(up);

        response.sendRedirect(request.getContextPath() + "/posts?id="+user.getId());

    }
}
