package app.web.servlets;

import app.util.FileUtil;
import app.util.FileUtilImpl;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private FileUtil fileUtil;

    private static final String FILE_PATH = "D:\\SoftUni-Tasks\\Java-Web-SoftUni\\Introduction to Java EE\\src\\main\\webapp\\views\\home.html";

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.fileUtil = new FileUtilImpl();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(this.fileUtil.readFile(FILE_PATH));
    }
}
