package dao;

import data.Post;
import database.DatabaseConnector;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class allPostDao {
//    private Connection connection=null;
//
//    public allPostDao(){
//
//    }
//
//    public ArrayList<Post> viewAllPosts(){
//        PreparedStatement pst=null;
//        ArrayList<Post> allPosts=new ArrayList<>();
//
//        try{
//            connection= DatabaseConnector.getInstance().getConnection();
//            pst=connection.prepareStatement("SELECT * FROM POST");
//            ResultSet rs= pst.executeQuery();
//
//            while(rs.next()){
//                int post_id=rs.getInt("post_id");
//                String username=rs.getString("username");
//                String email=rs.getString("email");
//                String postcontent=rs.getString("posts");
//
//                allPosts.add(new Post(post_id, username, email, postcontent));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return allPosts;
//    }


}
