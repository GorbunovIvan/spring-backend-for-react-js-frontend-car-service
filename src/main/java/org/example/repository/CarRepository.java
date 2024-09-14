package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class CarRepository {

    private final List<Car> cars = new ArrayList<>();

    public CarRepository() {
        // Dummy data
        save(Car.builder().model("Mercedes").carClass("E").modelCode("200").year(2010).build());
        save(Car.builder().model("Mercedes").carClass("C").modelCode("100").year(2015).build());
        save(Car.builder().model("BMW").carClass("X").modelCode("5").year(2004).build());
        save(Car.builder().model("Audi").carClass("A").modelCode("8").year(2021).build());
    }

    public List<Car> getAll() {
        log.info("Fetching all cars");
        return new ArrayList<>(cars);
    }

    public Optional<Car> getById(Integer id) {
        log.info("Fetching car by id={}", id);
        return cars.stream()
                .filter(car -> Objects.equals(car.getId(), id))
                .findAny();
    }

    public Optional<Car> save(Car car) {
        log.info("Saving new car: {}", car);
        var id = nextId();
        car.setId(id);
        cars.add(car);
        return Optional.of(car);
    }

    public Optional<Car> save(Integer id, Car car) {

        log.info("Saving car with id={}: {}", id, car);

        var carExistingOptional = getById(id);
        if (carExistingOptional.isEmpty()) {
            var message = String.format("Car with id=%d not found", id);
            log.warn(message);
            throw new RuntimeException(message);
        }

        var carExisting = carExistingOptional.get();

        if (car.getModel() != null) {
            carExisting.setModel(car.getModel());
        }
        if (car.getCarClass() != null) {
            carExisting.setCarClass(car.getCarClass());
        }
        if (car.getModelCode() != null) {
            carExisting.setModelCode(car.getModelCode());
        }
        if (car.getYear() != null) {
            carExisting.setYear(car.getYear());
        }

        return Optional.of(carExisting);
    }

    public void deleteById(Integer id) {
        log.info("Deleting car by id={}", id);
        var carExistingOptional = getById(id);
        if (carExistingOptional.isEmpty()) {
            return;
        }
        cars.remove(carExistingOptional.get());
    }

    private Integer nextId() {
        return cars.stream()
                .mapToInt(Car::getId)
                .max()
                .orElse(0) + 1;
    }
}
