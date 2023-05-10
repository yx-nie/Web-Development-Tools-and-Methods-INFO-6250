package demo;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FormDemo
 */
@WebServlet("/FormDemo")
public class FormDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("regi.html");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String middlename=request.getParameter("middlename");
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String postcode=request.getParameter("postcode");
		String country=request.getParameter("country");
		String mobile=request.getParameter("mobile");
		String work=request.getParameter("work");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String username=request.getParameter("username");
		
		System.out.println(gender);
//		PrintWriter pw =response.getWriter();
//		pw.append("success");
		
		HttpSession session=request.getSession();
		
		RegiData data=new RegiData();
		data.setFirstname(firstname);
		data.setLastname(lastname);
		data.setMiddlename(middlename);
		data.setAddress1(address1);
		data.setAddress2(address2);
		data.setCity(city);
		data.setState(state);
		data.setPostcode(postcode);
		data.setCountry(country);
		data.setMobile(mobile);
		data.setWork(work);
		data.setEmail(email);
		data.setUsername(username);
		data.setGender(gender);
		
		session.setAttribute("data", data);
		response.sendRedirect("ChangeFrom");
		
		
	
		
		
	}

}
