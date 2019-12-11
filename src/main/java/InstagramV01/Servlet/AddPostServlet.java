package InstagramV01.Servlet;

import InstagramV01.Interface.ResourceUserReader;
import InstagramV01.Interface.ResourceWriter;
import InstagramV01.WorkClasses.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.util.ArrayList;

@WebServlet("/upload")
@MultipartConfig
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String comment = "";
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        File uploadedFile = null;
        String filePath = "";

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        String root = getServletContext().getRealPath("/");
                        File path = new File(root + "/uploads");
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }
                        uploadedFile = new File(path + "/" + fileName);
                        item.write(uploadedFile);
                    } else{
                        comment = new String(item.get(), "UTF-8");
                    }
                }
                String path = String.valueOf(uploadedFile.getAbsolutePath());
                filePath = path.substring(path.lastIndexOf("Inst")+5, path.length());
            } catch (Exception e) {

                filePath = "";
            }
        }



        int id = Integer.parseInt(request.getParameter("id"));

        ResourceUserReader rur = new DBUserReader();
        User user = rur.getUserById(id);
        request.setAttribute("user", user);

        Post post = new Post(filePath, comment);
        if(post.getNone()<2){
            List<Post> posts = new ArrayList<>();
            posts.add(post);
            UserPost up = new UserPost(user, posts);

            ResourceWriter rw = new DBWriter();
            rw.saveNewPost(up);

            response.sendRedirect(request.getContextPath() + "/posts?id="+user.getId());
        } else{
            request.setAttribute("mes", "Вы не можете опубликовать пустой пост.\nДобавьте картинку или текст.");
            String nextJSP = "newPost.jsp?id="+user.getId();
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }

    }
}
