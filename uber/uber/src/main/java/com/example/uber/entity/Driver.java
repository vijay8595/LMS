package com.example.uber.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "adhaarNumber",nullable = false,unique = true)
    private String adhaarNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "active")
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "carNumber")
    private Car car;

    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    List<Booking> booking = new ArrayList<>();

}
