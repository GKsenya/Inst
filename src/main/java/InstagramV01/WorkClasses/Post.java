package InstagramV01.WorkClasses;

public class Post {
    private String date;
    private String comment = "";
    private String img = "";
    private int none = 0;


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

    public Post(String date, String img, String comment) {
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

    @Override
    public String toString() {
        return "Post{" +
                "date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
