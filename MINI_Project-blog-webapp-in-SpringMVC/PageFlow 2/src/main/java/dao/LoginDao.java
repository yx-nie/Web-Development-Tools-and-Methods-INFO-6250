package dao;

import data.UserSession;
import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private Connection connection=null;

    public LoginDao() {
    }

    public UserSession validateLogin(String username, String password){
        PreparedStatement pst=null;
        UserSession userSession=null;

        try{
            connection= DatabaseConnector.getInstance().getConnection();
            pst=connection.prepareStatement("SELECT * FROM USER WHERE username=? and password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();

            while(rs.next()){

                String usernameDB=rs.getString("username");
                String emailDB=rs.getString("email");
                String roleDB=rs.getString("role");
                String pwdDB=rs.getString("password");

                userSession=new UserSession(usernameDB,emailDB,roleDB,pwdDB);
                System.out.println("***LoginDao validLogin***"+userSession);


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userSession;
    }
}
