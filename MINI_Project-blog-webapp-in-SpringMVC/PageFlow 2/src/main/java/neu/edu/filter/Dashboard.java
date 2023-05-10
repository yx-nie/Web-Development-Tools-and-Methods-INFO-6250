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
@WebFilter( urlPatterns = {"/dashboard"})
public class Dashboard extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Dashboard() {
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
		// TODO Auto-generated method stub
		// place your code here

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session=req.getSession();
        UserSession usersession=(UserSession) session.getAttribute("usersession");
        if(usersession==null) {
//        	usersession.getRole();
        	res.sendRedirect(req.getContextPath()+"/login");
//        	req.getRequestDispatcher("/dashboard.jsp").forward(request, response);
        }else {
        	chain.doFilter(request, response);
        }
        
//        if("no".equals(request.getParameter("pass"))) {
//        res.sendRedirect(req.getContextPath() + "/login");
//        }else {
//        	chain.doFilter(request, response);
//        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
