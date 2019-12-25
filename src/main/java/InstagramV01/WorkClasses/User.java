package InstagramV01.WorkClasses;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String login;
    private String password;
    private int id;
    private List<Integer> likes;
    private List<User> friends;
    private List<Integer> friendsId = new ArrayList<>();
    private String date;

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

    public User(String login, String name, int id, String date) {
        this.name = name;
        this.login = login;
        this.id = id;
        this.date = date;
    }

    public User(String login, String name, int id, List<Integer> likes, List<User> friends) {
        this.name = name;
        this.login = login;
        this.id = id;
        this.likes = likes;
        this.friends = friends;
        this.getIds();
    }

    private void getIds(){
        for (User userf:this.friends){
            this.friendsId.add(userf.getId());
        }
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

    public List<User> getFriends() {
        return friends;
    }

    public List<Integer> getFriendsId() {
        return friendsId;
    }

    public String getDate() {
        return date;
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
