package app.spring.web.controllers;

import app.spring.service.models.CarServiceModel;
import app.spring.service.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("create");
    }

    @PostMapping("/create")
    public ModelAndView createCar(@ModelAttribute CarServiceModel model){
        this.carService.saveCar(model);
        return new ModelAndView("redirect:/cars/all");
    }

    @GetMapping("/all")
    public ModelAndView allCars(ModelAndView modelAndView){
        modelAndView.addObject("cars",this.carService.getAllCars());
        modelAndView.setViewName("all");
        return modelAndView;
    }
}
