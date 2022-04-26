package coach.xfitness.controller;

import java.io.IOException;
import java.util.Map;

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

import coach.xfitness.util.ParameterAsMapRequestWrapper;

@WebFilter({ "/exercise" })
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        /*
        if (session == null || session.getAttribute("user") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/authenticate");
            requestDispatcher.forward(request, response);
        } else {
        }*/

        ParameterAsMapRequestWrapper requestWrapper = new ParameterAsMapRequestWrapper(request);
        Map<String, Object> parameterAsMap = requestWrapper.getParameterAsMap("exercise");

        if (parameterAsMap != null && !parameterAsMap.isEmpty()) {
            @SuppressWarnings("unchecked")
            Map<String, String[]> exerciseMap = (Map<String, String[]>) parameterAsMap.get("exercise");
            exerciseMap.forEach((k, v) -> {
                System.out.println(k + ":" + v[0]);
            });
        }

        System.out.println();

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
