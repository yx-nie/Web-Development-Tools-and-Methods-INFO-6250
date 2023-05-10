package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DatabaseConnector {
    private static DatabaseConnector instance=null;
    private static Connection connection = null;

    private static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/messageboard";

    private static final String username = "alicia";
    private static final String password = "199103123220nyx";

    private DatabaseConnector(){
        try{
            Class.forName(JDBC_DRIVER);
            String url=DB_URL;
            String name=username;
            String pw=password;
            connection=DriverManager.getConnection(url, name, pw);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnector getInstance(){
        if(instance==null){
            instance=new DatabaseConnector();
        }
        try{
            if(instance.getConnection().isClosed()){
                instance=new DatabaseConnector();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }


}
