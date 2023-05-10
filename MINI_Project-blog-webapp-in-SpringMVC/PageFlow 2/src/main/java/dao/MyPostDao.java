package dao;

import data.Post;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyPostDao {
//    private Connection connection=null;
//
//    public MyPostDao(){
//
//    }
//
//    public ArrayList<Post> viewMyPosts(String username){
//        PreparedStatement pst=null;
//        ArrayList<Post> myPosts=new ArrayList<>();
//
//        try{
//            connection= DatabaseConnector.getInstance().getConnection();
//            pst=connection.prepareStatement("SELECT * FROM POST WHERE username=?");
//            pst.setString(1, username);
//            ResultSet rs=pst.executeQuery();
//
//            while(rs.next()){
//                int post_id=rs.getInt("post_id");
//                String un=rs.getString("username");
//                String email=rs.getString("email");
//                String post=rs.getString("posts");
//                myPosts.add(new Post(post_id, un, email, post));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return myPosts;
//    }
}
