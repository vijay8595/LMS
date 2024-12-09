package com.example.uber.controller;

import com.example.uber.dto.request.DriverRequest;
import com.example.uber.dto.response.DriverResponse;
import com.example.uber.entity.Driver;
import com.example.uber.service.DriverService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("uber/v1/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/create")
    public DriverResponse createDriver(@RequestBody DriverRequest driverRequest){
        return driverService.save(driverRequest);
    }

    @GetMapping()
    public Optional<Driver> driverById(@RequestParam("id") Integer id){
        return driverService.getDriverById(id);
    }
}
