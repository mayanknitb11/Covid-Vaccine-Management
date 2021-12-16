package com.example.dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDetailsRequest {
    private Integer userId;
    private String city;
    private String dose;
    private LocalDate date;
    private Integer centerId;
    private Integer slotId;
}
