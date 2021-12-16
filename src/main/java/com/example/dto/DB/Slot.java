package com.example.dto.DB;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Slot {

    private Integer id;
    private Boolean Dose1;
    private Boolean Dose2;
    private LocalDate date;
    private LocalTime time;
    private Integer centerId;
    private Integer covaxinAvailability;


    public Slot(Integer id, Boolean dose1, Boolean dose2, LocalDate date, LocalTime time, Integer centerId, Integer covaxinAvailability) {
        this.id = id;
        Dose1 = dose1;
        Dose2 = dose2;
        this.date = date;
        this.time = time;
        this.centerId = centerId;
        this.covaxinAvailability = covaxinAvailability;
    }


}
