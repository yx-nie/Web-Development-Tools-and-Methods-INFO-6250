package neu.edu.secenter.admin;

import dao.UserDao;
import data.UserRegistration;
import data.UserSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/viewUsers")
public class ViewUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao=new UserDao();

        ArrayList<UserRegistration> userRegistrations=userDao.viewAllUsers("user");
        HttpSession session=request.getSession();
        UserSession userSession=(UserSession) session.getAttribute("usersession");
        System.out.println(userSession.getRole()+userSession.getUsername()+userSession.getEmail());
//        request.setAttribute("UserRegistration", userRegistrations);

        RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/admin/viewUsers.jsp");
        rd.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
