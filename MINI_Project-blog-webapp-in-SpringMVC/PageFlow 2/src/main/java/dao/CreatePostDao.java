package dao;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatePostDao {
    Connection connection=null;

    public CreatePostDao(){

    }

    public void addPost(String username, String email, String input){
        PreparedStatement pst=null;

        try{
            connection= DatabaseConnector.getInstance().getConnection();
            pst=connection.prepareStatement("INSERT INTO POST(username, email, posts) VALUES (?,?,?)");
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, input);

            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
