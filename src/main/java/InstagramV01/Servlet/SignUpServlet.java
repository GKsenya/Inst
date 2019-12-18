package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceWriter;
import InstagramV01.WorkClasses.DBWriter;
import InstagramV01.WorkClasses.InfoValidation;
import InstagramV01.WorkClasses.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InfoValidation infoValidation = new InfoValidation(request);
        int exceptionCounter = infoValidation.isValid();
        String userLogin = infoValidation.getLogin();
        String userName = infoValidation.getName();
        String userPassword = infoValidation.getPassword();
        User user = new User(userLogin, userName, userPassword);
        ResourceWriter dbWriter = new DBWriter();
        dbWriter.saveNewUser(user);
        if(!dbWriter.isUserInDB()&exceptionCounter==0){
            request.setAttribute("mes", "Пользователь успешно зарегистрирован!");
            response.sendRedirect(request.getContextPath() + "/signin.jsp");
        } else {
            if (exceptionCounter == 0) {
                request.setAttribute("mes", "Пользователь уже зарегистрирован! Прейдите на поле входа, или зарегистрируйте нового!!");
            }
            request.setAttribute("login", userLogin);
            request.setAttribute("name", userName);
            request.setAttribute("password", userPassword);
            String nextJSP = "signup.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }
    }
}