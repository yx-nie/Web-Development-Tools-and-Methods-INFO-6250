package neu.edu.secenter.user;

import com.mongodb.MongoClient;
import dao.AdminDeletePostDao;
import dao.MongoDBPostDAO;
import data.UserSession;
import org.bson.types.ObjectId;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteMyPost", value = "/DeleteMyPost")
public class DeleteMyPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id = request.getParameter("_id");
        String username=request.getParameter("username");
        HttpSession session = request.getSession();
        UserSession usersession=(UserSession) session.getAttribute("usersession");
        if(username.equals(usersession.getUsername())){
            ServletContext application=request.getServletContext();
            MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
            MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
            mongoDBPostDAO.delete(_id);
        }else{
            //do nothing;
        }

        request.getRequestDispatcher("ViewMyPost").forward(request, response);



    }
}
