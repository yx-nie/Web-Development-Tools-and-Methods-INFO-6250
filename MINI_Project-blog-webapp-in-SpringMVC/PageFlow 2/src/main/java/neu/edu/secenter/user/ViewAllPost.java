package neu.edu.secenter.user;

import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;
import data.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewAllPost", value = "/ViewAllPost")
public class ViewAllPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application=request.getServletContext();
        MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
        MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
        List<Post> posts=mongoDBPostDAO.getList();
        request.setAttribute("posts", posts);

        request.getRequestDispatcher("/WEB-INF/user/viewAllPost.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
