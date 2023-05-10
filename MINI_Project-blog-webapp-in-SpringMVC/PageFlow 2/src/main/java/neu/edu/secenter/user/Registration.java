package neu.edu.secenter.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegiDao;
import data.UserRegistration;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Servlet implementation class Register
 */
@WebServlet(urlPatterns = {"/Registration","/registration"})
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/registration.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String errorMesg="";
		int returnValue=0;
		boolean regiStatus=false;

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		//rolename was set to user as only user need to be registered
		String rolename="user";
//		String rolename=request.getParameter("role");

		RegiDao regiDao=new RegiDao();
		returnValue=regiDao.saveUsers(username, password, email, rolename);
		if(returnValue!=0){

		    ServletContext application = request.getServletContext();

		    UserRegistration newuserregi = new UserRegistration(username,password, email, rolename);

		    ArrayList<UserRegistration> userRegistrations=(ArrayList<UserRegistration>) application.getAttribute("UserRegistration");
		    if(userRegistrations==null) {
			   userRegistrations=new ArrayList<UserRegistration>();
		    }

		    userRegistrations.add(newuserregi);

		    application.setAttribute("UserRegistration", userRegistrations);
			regiStatus=true;

			//send email
			Properties properties=System.getProperties();
			properties.setProperty("mail.smtp.host", "smtp-reply.sendinblue.com");
			properties.setProperty("mail.smtp.port", "587");
			properties.setProperty("mail.smtp.auth", "true");

			Session session=Session.getInstance(properties);
			try{
				MimeMessage mimeMessage=new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress("alicia312star@gmail.com"));
				mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
				mimeMessage.setSubject("Registration Info");
				mimeMessage.setText("Thank you for registering Blogs");

				Transport transport=session.getTransport("smtp");
				transport.connect("smtp-relay.sendinblue.com", "alicia312star@gmail.com", "X8Y4vcAKj23tsLCn");
				transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
				transport.close();

				System.out.println("email send");
			}catch (MessagingException e){
				System.out.println("Error sending email");
				e.printStackTrace();
			}
		}else{
			errorMesg="Username already taken. Please choose a different username.";
		}
		System.out.println("save users");

		if(regiStatus){
			
			response.sendRedirect("login");
		}else{
			request.setAttribute("error", errorMesg);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/user/registration.jsp");
			rd.forward(request,response);
		}


		
	}

}
