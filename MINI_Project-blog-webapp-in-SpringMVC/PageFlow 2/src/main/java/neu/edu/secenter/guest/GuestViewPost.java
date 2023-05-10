package neu.edu.secenter.guest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import dao.MongoDBPostDAO;
import data.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GuestViewPost", value = "/GuestViewPost")
public class GuestViewPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext application=request.getServletContext();
        MongoClient mongoClient= (MongoClient) application.getAttribute("mongodbClient");
        MongoDBPostDAO mongoDBPostDAO=new MongoDBPostDAO(mongoClient);
        List<Post> posts=mongoDBPostDAO.getList();
        List<Post> pureposts=new ArrayList<Post>();
        for(Post post: posts){
            Post post1=new Post();
            post1.setUsername(post.getUsername());
            post1.setPosts(post.getPosts());
            pureposts.add(post1);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonArray=objectMapper.writeValueAsString(pureposts);

        response.setContentType("application/json");
        response.getWriter().write(jsonArray);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
