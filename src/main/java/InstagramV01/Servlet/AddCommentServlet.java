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

public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        InfoValidation infoValidation = new InfoValidation(req);
        int exceptionCounter = infoValidation.isValidComment();
        String userComment = infoValidation.getLogin();

        int id = (Integer) req.getSession().getAttribute("id");
        int postId = Integer.parseInt(req.getParameter("postId"));
        ResourceUserReader rur = new DBUserReader();

        User user = rur.getUserById(id);
        Post post = new DBUserReader().getPostById(postId);
        List<Post> posts = new ArrayList<>();
        posts.add(post);


        if(exceptionCounter==0){
            ResourceWriter dbWriter = new DBWriter();
            dbWriter.saveComment(new UserPost(user, posts), req.getParameter("comment"));
            resp.sendRedirect(req.getContextPath() + "/posts");
        } else {
            String nextJSP = "newComment.jsp?postId=" + post.getId() + "&userId=" + user.getId();
            RequestDispatcher dispatcher = req.getRequestDispatcher(nextJSP);
            dispatcher.forward(req, resp);
        }

    }
}
