package neu.edu.secenter.user;

import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;
import data.Post;
import data.UserSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewMyPost", value = "/ViewMyPost")
public class ViewMyPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application=request.getServletContext();
        HttpSession session=request.getSession();
        UserSession userSession=(UserSession) session.getAttribute("usersession");
        String username=userSession.getUsername();
        MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
        MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
        List<Post> myposts= mongoDBPostDAO.getMyList(username);
        request.setAttribute("myposts",myposts);
        request.getRequestDispatcher("/WEB-INF/user/viewMyPost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
