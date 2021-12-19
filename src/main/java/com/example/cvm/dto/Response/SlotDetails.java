package com.example.cvm.dto.Response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class SlotDetails {
    private Integer slotId;
    private String dose;
    private LocalTime time;
    private Integer VaccineAvailability;
    public SlotDetails(Integer slotId, String dose, LocalTime time, Integer vaccineAvailability) {
        this.slotId = slotId;
        this.dose = dose;
        this.time = time;
        VaccineAvailability = vaccineAvailability;
    }
}
