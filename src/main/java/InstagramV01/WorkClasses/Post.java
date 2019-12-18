package InstagramV01.WorkClasses;

import java.util.List;

public class Post {
    private String date;
    private String comment = "";
    private String img = "";
    private int none = 0;
    private int id;
    private String likes;
    private List<Comment> comments = null;


    public Post(String img, String comment) {
        if (comment == null || comment.equalsIgnoreCase("")) {
            none ++;
        }
        if (img.equalsIgnoreCase("")) {
            none ++;
        }
        this.img = img;
        this.comment = comment;
    }

    public Post(String date, String img, String comment, int id, String likes, List<Comment> comments) {
        this.comments = comments;
        this.likes = likes;
        this.id = id;
        this.date = date;
        if (comment == null) {
            comment = "";
        }
        this.comment = comment;
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getImg() {
        return img;
    }

    public int getNone() {
        return none;
    }

    public int getId() {
        return id;
    }

    public String getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", img='" + img + '\'' +
                ", none=" + none +
                ", id=" + id +
                ", likes='" + likes + '\'' +
                ", comments=" + comments +
                '}';
    }
}
