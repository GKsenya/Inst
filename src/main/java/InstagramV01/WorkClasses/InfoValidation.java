package InstagramV01.WorkClasses;

import javax.servlet.http.HttpServletRequest;

public class InfoValidation {

    private String login = null;
    private String name = null;
    private String password = null;
    private String comment = null;
    private int exceptionCounter = 0;
    private HttpServletRequest request;

    public InfoValidation(HttpServletRequest request) {
        this.request = request;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int isValid(){

        if(!request.getParameter("login").equalsIgnoreCase("")){
            login = request.getParameter("login");
        }else{
            request.setAttribute("loginMes", "Заполните поле \"Логин\".");
            exceptionCounter ++;
        }
        if(!request.getParameter("name").equalsIgnoreCase("")){
            name = request.getParameter("name");
        }else{
            request.setAttribute("nameMes", "Заполните поле \"Имя\".");
            exceptionCounter ++;
        }
        if(!request.getParameter("password").equalsIgnoreCase("")){
            password = request.getParameter("password");
        }else{
            request.setAttribute("passwordMes", "Заполните поле \"Пароль\".");
            exceptionCounter ++;
        }
        return exceptionCounter;

    }

    public int isValidComment(){
        if(!request.getParameter("comment").equalsIgnoreCase("")){
            comment = request.getParameter("comment");
        }else{
            request.setAttribute("commentMes", "Невозможно сохранить пустой комментарий.");
            exceptionCounter ++;
        }
        return exceptionCounter;
    }
}
