package app.spring.service.services;

import app.spring.data.models.Car;
import app.spring.data.repositories.CarRepository;
import app.spring.service.models.CarServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CarServiceModel> getAllCars() {
        return this.carRepository
                .findAll()
                .stream()
                .map(c->this.modelMapper.map(c,CarServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveCar(CarServiceModel car) {
        this.carRepository.saveAndFlush(this.modelMapper.map(car, Car.class));
    }
}
