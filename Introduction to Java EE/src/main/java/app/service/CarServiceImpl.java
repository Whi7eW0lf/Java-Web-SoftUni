package app.service;

import app.domain.entities.Car;
import app.domain.models.service.CarServiceModel;
import app.domain.repository.CarDatabase;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;

    @Inject
    public CarServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCar(CarServiceModel carServiceModel) {
        CarDatabase.addCar(this.modelMapper.map(carServiceModel,Car.class));
    }

    @Override
    public List<CarServiceModel> allCars() {
        return CarDatabase.getCars()
                .stream()
                .map(e->this.modelMapper.map(e,CarServiceModel.class))
                .collect(Collectors.toList());
    }
}
