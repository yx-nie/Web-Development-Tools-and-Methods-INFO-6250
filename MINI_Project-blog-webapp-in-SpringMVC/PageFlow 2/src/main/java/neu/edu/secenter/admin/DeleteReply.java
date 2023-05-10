package neu.edu.secenter.admin;

import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;
import data.UserSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteReply", value = "/DeleteReply")
public class DeleteReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String post_id=request.getParameter("post_id");
        String reply_id=request.getParameter("reply_id");
        HttpSession session=request.getSession();
        UserSession userSession=(UserSession) session.getAttribute("usersession");
        String role=userSession.getRole().getRoleName();
        if(role=="admin"){
            ServletContext application=request.getServletContext();
            MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
            MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
            mongoDBPostDAO.deleteReply(post_id, reply_id);
        }else {
            //do nothing
        }

        request.getRequestDispatcher("ViewPosts").forward(request, response);
    }
}
