package dao;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDeletePostDao {
    private Connection connection=null;

    public AdminDeletePostDao(){

    }

    public void deletePost(int post_id){
        PreparedStatement pst=null;

        try{
            connection= DatabaseConnector.getInstance().getConnection();
            pst=connection.prepareStatement("DELETE FROM POST WHERE post_id=?");
            pst.setInt(1, post_id);

            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
