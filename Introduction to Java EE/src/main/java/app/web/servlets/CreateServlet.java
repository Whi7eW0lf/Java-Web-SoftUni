package app.web.servlets;

import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.service.CarServiceModel;
import app.service.CarService;
import app.service.CarServiceImpl;
import app.util.FileUtil;
import app.util.FileUtilImpl;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    private static final String FILE_PATH = "D:\\SoftUni-Tasks\\Java-Web-SoftUni\\Introduction to Java EE\\src\\main\\webapp\\views\\create.html";

    private FileUtil fileUtil;
    private CarService carService;
    private ModelMapper modelMapper;


    @Override
    public void init() {
        this.fileUtil = new FileUtilImpl();
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(this.modelMapper);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(this.fileUtil.readFile(FILE_PATH));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarCreateBindingModel bindingModel = new CarCreateBindingModel();
        bindingModel.setBrand(req.getParameter("brand"));
        bindingModel.setYear(Integer.parseInt(req.getParameter("year")));
        bindingModel.setEngine(req.getParameter("engine"));
        bindingModel.setModel(req.getParameter("model"));

        this.carService.createCar(this.modelMapper.map(bindingModel, CarServiceModel.class));

        resp.sendRedirect("/all");
    }
}
