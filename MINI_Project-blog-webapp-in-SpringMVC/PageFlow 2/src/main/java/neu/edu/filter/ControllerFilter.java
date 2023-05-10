package neu.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.UserSession;

/**
 * Servlet Filter implementation class Dashboard
 */
@WebFilter( urlPatterns = {"/controller"})
public class ControllerFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
	public ControllerFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute("usersession");
		System.out.println("***controller filter****"+userSession.getRole()+userSession.getUsername()+userSession.getEmail());
        
        boolean testing = false;
		if(testing){
			userSession = new UserSession();
			userSession.setCurrentPage("AddReply");
			session.setAttribute("usersession",userSession);
			chain.doFilter(request, response);

		}else if(userSession == null) {
            // Redirect to login
            res.sendRedirect(req.getContextPath() + "/login");
        }else {
            //Continue to Servlet
            chain.doFilter(request, response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
