package coach.xfitness.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({ "/configure", "/exercise", "/planner", "/workout" })
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * If the user is not authenticated, redirect to the authentication page
     * 
     * @param servletRequest The request object that is passed to the servlet.
     * @param servletResponse The response object that is passed to the filter.
     * @param chain The FilterChain object that represents the filter chain to which
     * this filter belongs.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("authenticate", true);
            request.getRequestDispatcher("/authenticate")
                    .include(request, response);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
