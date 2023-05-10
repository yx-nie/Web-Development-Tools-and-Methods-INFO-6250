package neu.edu.secenter.admin;

import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;
import data.UserSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePost", value = "/DeletePost")
public class DeletePost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id= request.getParameter("_id");
        HttpSession session = request.getSession();
        UserSession usersession=(UserSession) session.getAttribute("usersession");
        String role=usersession.getRole().getRoleName();
        if(role=="admin"){
            ServletContext application=request.getServletContext();
            MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
            MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
            mongoDBPostDAO.delete(_id);

        }else{
            //do nothing;
        }
        request.getRequestDispatcher("ViewPosts").forward(request, response);

    }
}
