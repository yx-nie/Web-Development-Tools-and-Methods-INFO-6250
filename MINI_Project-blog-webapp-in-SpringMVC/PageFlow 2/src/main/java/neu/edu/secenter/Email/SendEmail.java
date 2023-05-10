package neu.edu.secenter.Email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TreeMap;

@WebServlet(name = "SendEmail", value = "/SendEmail")
public class SendEmail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();
        Properties properties=System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp-reply.sendinblue.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");

        Session session=Session.getInstance(properties);
        try{
            MimeMessage mimeMessage=new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("alicia312star@gmail.com"));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress("nie.yo@northeastern.edu")));
            mimeMessage.setSubject("Subject");
            mimeMessage.setText("Hello World");

            Transport transport=session.getTransport("smtp");
            transport.connect("smtp-relay.sendinblue.com", "alicia312star@gmail.com", "X8Y4vcAKj23tsLCn");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

            out.println("email send");
        }catch (MessagingException e){
            out.println("Error");
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
