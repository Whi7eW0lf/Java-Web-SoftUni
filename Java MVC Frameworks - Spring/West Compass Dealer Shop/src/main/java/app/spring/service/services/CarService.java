package app.spring.service.services;

import app.spring.service.models.CarServiceModel;

import java.util.List;

public interface CarService {
    List<CarServiceModel> getAllCars();

    void saveCar(CarServiceModel car);
}
