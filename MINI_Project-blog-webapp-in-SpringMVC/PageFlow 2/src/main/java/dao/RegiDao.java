package dao;

import data.UserRegistration;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegiDao {
    Connection connection=null;
    PreparedStatement pst=null;

    public RegiDao(){

    }

    public int saveUsers(String name, String password, String email, String role){

        int returnValue=0;
        connection= DatabaseConnector.getInstance().getConnection();


        try{
            if(connection==null || connection.isClosed()){
                connection=DatabaseConnector.getInstance().getConnection();
            }
            pst=connection.prepareStatement("INSERT INTO USER(username, first_name, last_name, password, email, role) VALUES (?,?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, String.valueOf(JDBCType.VARCHAR));
            pst.setString(3, String.valueOf(JDBCType.VARCHAR));
            pst.setString(4, password);
            pst.setString(5, email);
            pst.setString(6, role);
            returnValue=pst.executeUpdate();
        }catch (SQLException e){
            returnValue=0;
            e.printStackTrace();
        }
        return returnValue;
    }

    public int updateUser(String name, String pwd, String email){
        int success=0;
        connection= DatabaseConnector.getInstance().getConnection();

        try{
            if(connection==null || connection.isClosed()){
                connection=DatabaseConnector.getInstance().getConnection();
            }

            pst=connection.prepareStatement("UPDATE USER SET password=?, email=? WHERE username=?");
            pst.setString(1, pwd);
            pst.setString(2, email);
            pst.setString(3, name);
            success=pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return success;
    }
}
