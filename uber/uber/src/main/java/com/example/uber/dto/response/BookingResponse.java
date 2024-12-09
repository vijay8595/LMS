package com.example.uber.dto.response;

import com.example.uber.entity.Driver;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private Integer bookingId;
    private Integer customerId;
    private Driver driver;
    private String pickupLocation;
    private String dropLocation;
    private Double price;
}
