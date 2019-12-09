package InstagramV01.WorkClasses;

import java.io.FileOutputStream;
import java.sql.Blob;

public class Post {
    private String date;
    private String comment;
    private byte[] img;
    private Blob imgIn;

    public Post(String date, byte[] img, String comment) {
        this.date = date;
        this.comment = comment;
        this.img = img;
    }

    public Post(Blob imgIn, String comment) {
        this.imgIn = imgIn;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public byte[] getImg() {
        return img;
    }

    public Blob getImgIn() {
        return imgIn;
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
