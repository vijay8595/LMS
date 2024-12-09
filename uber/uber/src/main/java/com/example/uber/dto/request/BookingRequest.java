    package com.example.uber.dto.request;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class BookingRequest {
        private Integer customerId;
        private Integer driverId;
        private String pickupLocation;
        private String dropLocation;
        private String startTime;
        private LocalDate bookingDate;
    }
