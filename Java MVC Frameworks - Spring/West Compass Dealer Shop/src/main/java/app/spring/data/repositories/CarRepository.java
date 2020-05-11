package app.spring.data.repositories;

import app.spring.data.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,String> {
}
