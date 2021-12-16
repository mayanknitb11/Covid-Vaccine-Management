package com.example.dto.Response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class SlotDetails {
    public SlotDetails(Integer slotId, String dose, LocalTime time, Integer vaccineAvailability) {
        this.slotId = slotId;
        this.dose = dose;
        this.time = time;
        VaccineAvailability = vaccineAvailability;
    }

    private Integer slotId;
    private String dose;
    private LocalTime time;
    private Integer VaccineAvailability;
}
