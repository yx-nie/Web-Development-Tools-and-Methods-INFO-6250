package dao;

import data.UserRegistration;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;

    public UserDao(){

    }

    public ArrayList<UserRegistration> viewAllUsers(String role){
        PreparedStatement pst=null;
        ArrayList<UserRegistration> userRegistrations=new ArrayList<>();

        try{
            connection= DatabaseConnector.getInstance().getConnection();
            if(role!=null){
                pst=connection.prepareStatement("SELECT * FROM USER WHERE ROLE=(?)");
                pst.setString(1,role);
            }else{
                pst=connection.prepareStatement("SELECT * FROM USER");
            }

            ResultSet rs=pst.executeQuery();

            while(rs.next()){
                String usernameDB=rs.getString("username");
                String emailDB=rs.getString("email");
                String roleDB=rs.getString("role");
                String pwdDB=rs.getString("password");

                userRegistrations.add(new UserRegistration(usernameDB, emailDB, roleDB, pwdDB));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                pst.close();
            }catch (SQLException e2){
                e2.printStackTrace();
            }

        }
        return userRegistrations;
    }
}
