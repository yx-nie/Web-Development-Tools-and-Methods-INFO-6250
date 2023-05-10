package neu.edu.secenter.pagecontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UserRegistration;
import data.UserSession;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		UserSession usersession=(UserSession) session.getAttribute("usersession");
		String currentPage = usersession.getCurrentPage();
		UserRegistration.Role role=usersession.getRole();
		System.out.println("***controller****"+role);
		String dispatcher="";
		System.out.println(currentPage+"1");
		
		String currentaction=request.getParameter("action");
        if(currentPage!=null && currentaction!=null){
			currentPage=currentaction;
			usersession.setCurrentPage(currentPage);
			System.out.println(currentaction+"2");
		}

		switch (role) {
			case USER:
				if ("ViewAllPost".equals(currentPage)) {
					usersession.setCurrentPage("ViewAllPost");
					dispatcher = "ViewAllPost";
					System.out.println(currentPage + "3");
				} else if ("ViewMyPost".equals((currentPage))) {
					usersession.setCurrentPage("ViewMyPost");
					dispatcher = "ViewMyPost";
					System.out.println(currentPage + "4");
				} else if ("UserProfile".equals(currentPage)) {
					usersession.setCurrentPage("UserProfile");
					dispatcher = "UserProfile";
					System.out.println(currentPage + "5");
				} else if ("DeleteMyReply".equals((currentPage))) {
					usersession.setCurrentPage("DeleteMyReply");
					dispatcher = "DeleteMyReply";
					System.out.println(currentPage + "6");
				} else if ("DeleteMyPost".equals(currentPage)) {
					usersession.setCurrentPage("DeleteMyPost");
					dispatcher = "DeleteMyPost";
					System.out.println(currentPage + "7");
				} else if ("CreateMyPost".equals(currentPage)) {
					usersession.setCurrentPage("CreateMyPost");
					dispatcher = "CreateMyPost";
					System.out.println(currentPage + "8");
				}else if ("AddReply".equals(currentPage)) {
					usersession.setCurrentPage("AddReply");
					dispatcher = "AddReply";
					System.out.println(currentPage + "9");
				}

				RequestDispatcher rd = request.getRequestDispatcher(dispatcher);
				System.out.println(dispatcher);
				rd.forward(request, response);
				break;


			case ADMIN:
				if ("admindashboard".equals(currentPage)) {
					usersession.setCurrentPage("admindashboard");
					dispatcher = "admindashboard";
					System.out.println(currentPage + "5");
				} else if ("viewUsers".equals(currentPage)) {
					usersession.setCurrentPage("viewUsers");
					dispatcher = "viewUsers";
					System.out.println(currentPage + "10");
				} else if ("ViewPosts".equals(currentPage)) {
					usersession.setCurrentPage("ViewPosts");
					dispatcher = "ViewPosts";
					System.out.println(currentPage + "11");
				}  else if ("DeletePost".equals(currentPage)) {
					usersession.setCurrentPage("DeletePost");
					dispatcher = "DeletePost";
					System.out.println(currentPage + "12");
				} else if ("DeleteReply".equals(currentPage)) {
					usersession.setCurrentPage("DeleteReply");
					dispatcher = "DeleteReply";
					System.out.println(currentPage + "13");
				}

				rd = request.getRequestDispatcher(dispatcher);
				System.out.println(dispatcher);
				rd.forward(request, response);
				break;

			default:
				session.invalidate();
				response.sendRedirect("index.html");
		}

		}


		
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("go to frontcontroller dopost");
		doGet(request, response);
	}

}
