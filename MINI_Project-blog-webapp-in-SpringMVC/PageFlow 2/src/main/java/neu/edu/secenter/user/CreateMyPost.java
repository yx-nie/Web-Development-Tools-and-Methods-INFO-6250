package neu.edu.secenter.user;

import com.mongodb.MongoClient;
import dao.CreatePostDao;
import dao.MongoDBPostDAO;
import data.Post;
import data.UserSession;
import org.bson.types.ObjectId;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateMyPost", value = "/CreateMyPost")
public class CreateMyPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username=request.getParameter("username");
        System.out.println(username);
        String email=request.getParameter("email");
        System.out.println(email);
        String input=request.getParameter("newpost");
        System.out.println(input);
        ObjectId newId=new ObjectId();
        String _id=newId.toString();
        System.out.println(_id);
        HttpSession session = request.getSession();
        UserSession usersession=(UserSession) session.getAttribute("usersession");
        if(username.equals(usersession.getUsername())){
            ServletContext application=request.getServletContext();
            MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
            MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
            Post post=new Post();
            post.setUsername(username);
            post.setEmail(email);
            post.setPosts(input);
            mongoDBPostDAO.create(post);
        }else{
            //do nothing;
        }

        request.getRequestDispatcher("ViewMyPost").forward(request, response);

    }
}
