package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangeFrom
 */
@WebServlet("/ChangeFrom")
public class ChangeFrom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeFrom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
		RegiData newdata = (RegiData) session.getAttribute("data");
		
		PrintWriter pw= response.getWriter();
		pw.append("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Document</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <div>\n"
				+ "        First Name :\n"+ newdata.getFirstname()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Last Name :\n"+ newdata.getLastname()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Middle Name :\n"+ newdata.getMiddlename()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Address 1 :\n"+ newdata.getAddress1()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Address 2 :\n"+ newdata.getAddress2()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        City :\n"+ newdata.getCity()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        State :\n"+ newdata.getState()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Post Code :\n"+ newdata.getPostcode()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Country :\n"+ newdata.getCountry()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Mobile :\n"+ newdata.getMobile()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Work :\n"+ newdata.getWork()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Email :\n" + newdata.getEmail()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Gender :\n"+ newdata.getGender()
				+ "    </div>\n"
				+ "    <div>\n"
				+ "        Username :\n"+ newdata.getUsername()
				+ "    </div>\n"
				+ "</body>\n"
				+ "</html>"
				);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
