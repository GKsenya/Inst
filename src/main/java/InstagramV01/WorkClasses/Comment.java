package InstagramV01.WorkClasses;

public class Comment {
    private User user;
    private String date;
    private String comment;
    private int postId;

    public Comment(User user, String date, String comment, int postId) {
        this.user = user;
        this.date = date;
        this.comment = comment;
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public int getPostId() {
        return postId;
    }
}
