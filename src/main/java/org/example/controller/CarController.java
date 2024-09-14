package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Car;
import org.example.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    
    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        var cars = carService.getAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable Integer id) {
        var car = carService.getById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
        var carPersisted = carService.create(car);
        return ResponseEntity.ok(carPersisted);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable Integer id, @RequestBody Car car) {
        var carPersisted = carService.update(id, car);
        return ResponseEntity.ok(carPersisted);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        carService.deleteById(id);
    }
}
