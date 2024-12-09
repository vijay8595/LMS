package com.example.uber.service;

import com.example.uber.dto.request.DriverRequest;
import com.example.uber.dto.response.DriverResponse;
import com.example.uber.entity.Car;
import com.example.uber.entity.Driver;
import com.example.uber.exception.DriverNotFoundException;
import com.example.uber.repository.CarRepository;
import com.example.uber.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;
    public DriverResponse save(DriverRequest driverRequest) {

        Car car = new Car();
        car.setCarName(driverRequest.getCar());
        car.setCarNumber(driverRequest.getCarNumber());
        car.setCarType(driverRequest.getCarType());

        carRepository.save(car);
        Driver driver = Driver.builder()
                .name(driverRequest.getName())
                .adhaarNumber(driverRequest.getAdhaarNumber())
                .phone(driverRequest.getPhone())
                .car(car)
                .build();
        driverRepository.save(driver);
        DriverResponse driverResponse = new DriverResponse();
        driverResponse.setId(driver.getId());
        driverResponse.setName(driver.getName());
        driverResponse.setCarNumber(driverRequest.getCarNumber());
        return driverResponse;



    }

    public Optional<Driver> getDriverById(Integer id) {
        return driverRepository.findById(id);
    }
}
