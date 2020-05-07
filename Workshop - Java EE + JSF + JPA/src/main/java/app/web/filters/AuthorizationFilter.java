package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/faces/views/home.xhtml","/faces/views/add-job.xhtml", "/faces/views/job-details.xhtml","/faces/views/delete-job.xhtml",
"/views/home.xhtml","/views/add-job.xhtml","/views/job-details.xhtml","/views/delete-job.xhtml",
"/views/add-job.jsf","/views/home.jsf","/views/job-details.jsf","/views/delete-job.jsf","/views/home.jsf"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String isLogged = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("username");

        if (isLogged == null){
            ((HttpServletResponse) servletResponse).sendRedirect("/views/login.jsf");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
