package com.example.uber.entity;

import com.example.uber.Enum.CarType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car")
public class Car {
    @Id
    @Column(name = "car_number",unique = true)
    private String carNumber;

    @Column(name = "carName",nullable = false)
    private String carName;



    @Column(name = "carType")
    private CarType carType;

    @OneToOne(mappedBy = "car")
    @JsonIgnore
    private Driver driver;
}
