package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceWriter;
import InstagramV01.WorkClasses.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FriendsChangingServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = (Integer) req.getSession().getAttribute("id");
        int friendId = Integer.parseInt(req.getParameter("userId"));

        ResourceWriter dbWriter = new DBWriter();
        dbWriter.saveFriend(id, friendId);
        resp.sendRedirect(req.getContextPath() + "/allFriend");
    }
}
