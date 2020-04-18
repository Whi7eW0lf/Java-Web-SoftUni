package app.web.servlets;

import app.domain.models.view.CarViewModel;
import app.service.CarService;
import app.service.CarServiceImpl;
import app.util.FileUtil;
import app.util.FileUtilImpl;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/all")
public class AllServlet extends HttpServlet {

    private static final String FILE_PATH = "D:\\SoftUni-Tasks\\Java-Web-SoftUni\\Introduction to Java EE\\src\\main\\webapp\\views\\all.html";
    private FileUtil fileUtil;
    private ModelMapper modelMapper;
    private CarService carService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.fileUtil = new FileUtilImpl();
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(this.modelMapper);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(FILE_PATH);

        StringBuilder sb = new StringBuilder();

        List<CarViewModel> cars = this.carService.allCars()
                .stream()
                .map(e->this.modelMapper.map(e,CarViewModel.class))
                .collect(Collectors.toList());

        cars.forEach(e->sb.append(String.format(
                "<li class=\"d-flex justify-content-around\">%n" +
                "<div class=\"class=col-md-4 d-flex flex-column text-center mb-3\">%n" +
                "<h2 class=\"text-white text-center\">Brand: %s%n" +
                "<h4 class=\"text-white text-center\">Model: %s%n" +
                "<h4 class=\"text-white text-center\">Year: %s%n" +
                "<h4 class=\"text-white text-center\">Engine: %s%n" +
                "</div>%n" +
                "</li>",e.getBrand(),e.getModel(),e.getYear(),e.getEngine())));

        html = html.replace("{{replace}}",sb.toString());

        resp.getWriter().println(html);
    }
}
