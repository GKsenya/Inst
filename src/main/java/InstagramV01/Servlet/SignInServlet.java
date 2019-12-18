package InstagramV01.Servlet;

import InstagramV01.WorkClasses.DBUserSignIn;
import InstagramV01.WorkClasses.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        DBUserSignIn DBuserSignIn = new DBUserSignIn();
        User user = DBuserSignIn.userSignIn(userLogin, userPassword);

        if (user == null){
            request.setAttribute("mes", "Неверно введён логин или пароль");
            String nextJSP = "signin.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }
        else {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("id", user.getId());
            response.sendRedirect(request.getContextPath() + "/posts");
        }
    }
}
