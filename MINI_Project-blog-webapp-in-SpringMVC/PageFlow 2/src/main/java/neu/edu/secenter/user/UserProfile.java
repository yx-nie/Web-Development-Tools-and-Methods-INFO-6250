package neu.edu.secenter.user;

import dao.RegiDao;
import data.UserSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserProfile", value = "/UserProfile")
public class UserProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/user/userprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        UserSession userSession=(UserSession) session.getAttribute("usersession");

        RegiDao regiDao=new RegiDao();

        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");

        int updateresult=regiDao.updateUser(username,password,email);
        if(updateresult!=0){
            userSession.setEmail(email);
            userSession.setPwd(password);
        }else{
            //do nothing
        }

        request.getRequestDispatcher("/WEB-INF/user/userprofile.jsp").forward(request, response);

    }
}
