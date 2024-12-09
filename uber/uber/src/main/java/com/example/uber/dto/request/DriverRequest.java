package com.example.uber.dto.request;

import com.example.uber.Enum.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequest {
    private String name;

    private String adhaarNumber;

    private String phone;

    private String carNumber;

    private CarType carType;

    private  String car;
}
