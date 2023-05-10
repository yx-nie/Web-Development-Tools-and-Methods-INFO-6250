package database;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/testDatabase")
public class TestDatabase extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        Connection conn=DatabaseConnector.getInstance().getConnection();
        Statement stm=null;

        try{
            stm= conn.createStatement();
            String sql;
            sql="SELECT * FROM user";
            ResultSet rs=stm.executeQuery(sql);

            while(rs.next()){
                String email=rs.getString("email");
                String first_name=rs.getString("first_name");
                String last_name=rs.getString("last_name");
                String password=rs.getString("password");

                out.println("Email: "+email+"<br>");
                out.println(", First Name: "+first_name+"<br>");
                out.println(", Last Name: "+last_name+"<br>");
                out.println(", Password: "+password+"<br>");
            }

            out.println("</body></html>");
            rs.close();
            stm.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(stm!=null){
                    stm.close();
                }
            }catch (SQLException e2){
                e2.printStackTrace();
            }

            try{
                if(conn!=null){
                    conn.close();
                }
            }catch (SQLException e3){
                e3.printStackTrace();
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
