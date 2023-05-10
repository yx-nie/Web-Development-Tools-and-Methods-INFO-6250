package neu.edu.secenter.user;

import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;
import data.Post;
import data.Reply;
import data.UserSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddReply")
public class AddReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id=request.getParameter("_id");
        System.out.println("go to AddReply doGET");
        ServletContext application=request.getServletContext();
        MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
        MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
        Post post=mongoDBPostDAO.getPost(_id);

        List<Reply> replies=post.getReplies();


        request.setAttribute("post",post);
        request.setAttribute("replies", replies);


        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/user/addReply.jsp");
        rd.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String input=request.getParameter("newreply");
        String post_id=request.getParameter("post_id");
        System.out.println(username+email+input+post_id);

        Reply reply=new Reply(post_id, username,email,input);
        ServletContext application =request.getServletContext();
        MongoClient mongoClient=(MongoClient) application.getAttribute("mongodbClient");
        MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
        Post post=mongoDBPostDAO.getPost(post_id);
        mongoDBPostDAO.addReply(post, reply);
        Post renewedpost=mongoDBPostDAO.getPost(post_id);
        System.out.println("****AddReply Servlet***"+renewedpost);
        request.setAttribute("post", renewedpost);
        List<Reply> replies=renewedpost.getReplies();
        request.setAttribute("replies", replies);

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/user/addReply.jsp");
        rd.forward(request,response);
    }
}
