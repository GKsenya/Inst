package InstagramV01.WorkClasses;

import java.util.List;

public class User {
    private String name;
    private String login;
    private String password;
    private int id;
    private List<Integer> likes;

    public User(String login, String name, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String login, String name, int id) {
        this.name = name;
        this.login = login;
        this.id = id;
    }

    public User(String login, String name, int id, List<Integer> likes) {
        this.name = name;
        this.login = login;
        this.id = id;
        this.likes = likes;
    }


    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", id=" + id +
                '}';
    }
}
