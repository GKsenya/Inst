package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceUserReader;
import InstagramV01.WorkClasses.DBUserReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllPostsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("id");
        ResourceUserReader rur = new DBUserReader();
        req.setAttribute("user", rur.getUserById(id));
        req.setAttribute("posts", rur.readAllPosts());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("userPosts.jsp");
        requestDispatcher.forward(req, resp);
    }
}
