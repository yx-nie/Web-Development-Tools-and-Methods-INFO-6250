package neu.edu.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.mongodb.MongoException;
import com.mongodb.MongoClient;


@WebListener
public class MongoDBContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public MongoDBContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        try {
            ServletContext application = sce.getServletContext();
            MongoClient mongoDBClient = new MongoClient(application.getInitParameter("MONGODB_HOST"), Integer.parseInt(application.getInitParameter("MONGODB_PORT")));
            System.out.println("MongoDBClient initialized successfully");
            application.setAttribute("mongodbClient", mongoDBClient);
        } catch (MongoException e) {
            throw new RuntimeException("MongoClient init failed");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
