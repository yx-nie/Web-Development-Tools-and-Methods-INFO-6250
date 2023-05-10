package neu.edu.secenter.admin;

import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;
import data.Post;
import data.UserSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewPosts")
public class ViewPosts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        UserSession userSession=(UserSession) session.getAttribute("usersession");
        System.out.println(userSession.getRole()+userSession.getUsername()+userSession.getEmail());

        ServletContext application = request.getServletContext();
        MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
        MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
        List<Post> posts=mongoDBPostDAO.getList();
        request.setAttribute("posts", posts);

        RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/admin/viewPosts.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
