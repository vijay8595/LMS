package com.example.uber.service;

import com.example.uber.dto.request.BookingRequest;
import com.example.uber.dto.response.BookingResponse;
import com.example.uber.entity.Booking;
import com.example.uber.entity.Customer;
import com.example.uber.entity.Driver;
import com.example.uber.repository.BookingRepository;
import com.example.uber.repository.CustomerRepository;
import com.example.uber.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PriceCalculaterService priceCalculaterService;

    public BookingResponse createBooking(BookingRequest bookingRequest){
        Driver driver = driverService.getDriverById(bookingRequest.getDriverId())
        .orElseThrow(() -> new RuntimeException("Driver Not Exist For This Id " + bookingRequest.getDriverId()));
        Customer customer = customerService.findById(bookingRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Driver Not Exist For This Id " + bookingRequest.getDriverId()));

        double newPrice = priceCalculaterService.getPrice(bookingRequest.getPickupLocation(),bookingRequest.getDropLocation(),driver.getCar().getCarType());

        Booking booking = bookingRepository.save(Booking.builder()
                .driver(driver)
                .customer(customer)
                .dropLocation(bookingRequest.getDropLocation())
                .pickupLocation(bookingRequest.getPickupLocation())
                .startTime(bookingRequest.getStartTime())
                .price(newPrice)
                .bookingDate(bookingRequest.getBookingDate())
                .build());

        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .customerId(booking.getCustomer().getCustomerId())
                .driver(booking.getDriver())
                .price(booking.getPrice())
                .pickupLocation(booking.getPickupLocation())
                .dropLocation(booking.getDropLocation())
                .build();
    }
}
