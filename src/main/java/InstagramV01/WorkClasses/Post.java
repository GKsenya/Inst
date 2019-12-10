package InstagramV01.WorkClasses;


import java.util.Arrays;

public class Post {
    private String date;
    private String comment;
    private byte[] img;
    private String imgIn;


    public Post(String imgIn, String comment) {
        this.imgIn = imgIn;
        this.comment = comment;
    }
    public Post(String date, byte[] img, String comment) {
        this.date = date;
        this.comment = comment;
        this.img = img;
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

    public String getImgIn() {
        return imgIn;
    }

    @Override
    public String toString() {
        return "Post{" +
                "date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", img=" + Arrays.toString(img) +
                ", imgIn='" + imgIn + '\'' +
                '}';
    }
}
