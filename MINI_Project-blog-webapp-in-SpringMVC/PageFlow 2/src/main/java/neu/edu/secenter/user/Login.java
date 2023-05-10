package neu.edu.secenter.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import data.UserRegistration;
import data.UserSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(
		urlPatterns =  {"/Login","/login"},
		initParams = {@WebInitParam(name = "user1@gmail.com",value = "pass1")}
)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/user/login.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		boolean loginStatus=false;
		String erroMsg="Error: Invalid username or password";


		LoginDao loginDao=new LoginDao();
		UserSession userSession=loginDao.validateLogin(username, password);
		System.out.println(userSession.getRole()+userSession.getEmail()+userSession.getUsername());


		if(userSession!=null){
			HttpSession session=request.getSession();

			if(UserRegistration.Role.ADMIN==userSession.getRole()){
				userSession.setCurrentPage("viewUsers");
			}else {
				userSession.setCurrentPage("ViewAllPost");
			}

			session.setAttribute("usersession", userSession);
			System.out.println("***Login***" + userSession);

			loginStatus=true;
		}else {
			erroMsg="Error: Invalid username or password";
		}

		if(loginStatus){
			response.sendRedirect("controller");
		}else{
			request.setAttribute("error", erroMsg);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/user/login.jsp");
			rd.forward(request,response);
		}

		
	}

}
