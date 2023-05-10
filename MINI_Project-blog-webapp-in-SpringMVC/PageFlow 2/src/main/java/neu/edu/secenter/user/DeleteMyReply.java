package neu.edu.secenter.user;

import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteMyReply", value = "/DeleteMyReply")
public class DeleteMyReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String reply_id=request.getParameter("reply_id");
        String post_id=request.getParameter("post_id");
        ServletContext application=request.getServletContext();
        MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
        MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
        mongoDBPostDAO.deleteReply(post_id, reply_id);

        request.getRequestDispatcher("ViewAllPost").forward(request, response);


    }
}
