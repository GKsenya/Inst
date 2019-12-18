package InstagramV01.WorkClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class TestVersion {
     public static void main(String[] args){


         DBConnector dbConnector = new DBConnector();
         Connection con = dbConnector.createConnection();
         String sql = "SELECT `id`, `img` FROM test ";
         try {

                 File file = new File("C:\\Users\\Gorks\\Desktop\\НГУ.Маркетинг\\cfBKGfbITlg.jpg");
                 FileInputStream fis = new FileInputStream(file);
                 PreparedStatement ps = con.prepareStatement("INSERT INTO `test`(`id`, `img`) VALUES (?, ?)");
                 ps.setInt(1, 4);
                 ps.setBinaryStream(2, fis, (int)file.length());
                 ps.executeUpdate();
                 ps.close();
                 fis.close();

             PreparedStatement stmt = con.prepareStatement(sql);

             ResultSet resultSet = stmt.executeQuery();
             while (resultSet.next()) {
                 File image = new File("D:\\java.jpg");
                 FileOutputStream fos = new FileOutputStream(image);

                 byte[] buffer = new byte[1];
                 InputStream is = resultSet.getBinaryStream(2);
                 while (is.read(buffer) > 0) {
                     fos.write(buffer);
                 }
                 fos.close();
             }
             con.close();
         }catch (Exception e){e.printStackTrace();}
     }
}

