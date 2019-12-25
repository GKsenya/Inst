package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceUserReader;
import InstagramV01.WorkClasses.DBUserReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllFriendsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("id");
        DBUserReader dbUserReader = new DBUserReader();
        req.setAttribute("user", dbUserReader.getUserById(id));
        req.setAttribute("friends", dbUserReader.getFriends(id));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("userFriends.jsp");
        requestDispatcher.forward(req, resp);
    }
}
