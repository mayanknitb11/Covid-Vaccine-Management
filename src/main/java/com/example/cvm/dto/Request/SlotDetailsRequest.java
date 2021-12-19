package com.example.cvm.dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SlotDetailsRequest {
    private String city;
    private String dose;
    private LocalDate date;
    public SlotDetailsRequest(String city, String dose, LocalDate date) {
        this.city = city;
        this.dose = dose;
        this.date = date;
    }
}
