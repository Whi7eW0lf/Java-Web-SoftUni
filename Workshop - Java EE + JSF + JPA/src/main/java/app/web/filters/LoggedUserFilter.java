package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/faces/views/index.xhtml","/faces/views/login.xhtml","/faces/views/register.xhtml",
        "/faces/views/index.jsf","/faces/views/login.jsf","/faces/views/register.jsf",
        "/views/index.jsf","/views/login.jsf","/views/register.jsf",
        "/views/index.xhtml","/views/login.xhtml","/views/register.xhtml"})
public class LoggedUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String isLogged = (String) ((HttpServletRequest) request).getSession().getAttribute("username");

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAa");
        System.out.println(isLogged);

        if (isLogged != null){
            ((HttpServletResponse) response).sendRedirect("/views/home.jsf");
            return;
        }

        chain.doFilter(request,response);
    }
}
