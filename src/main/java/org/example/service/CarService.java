package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Car;
import org.example.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.getAll();
    }

    public Car getById(Integer id) {
        return carRepository.getById(id)
                .orElse(null);
    }

    public Car create(Car car) {
        return carRepository.save(car)
                .orElse(null);
    }

    public Car update(Integer id, Car car) {
        return carRepository.save(id, car)
                .orElse(null);
    }

    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }
}
