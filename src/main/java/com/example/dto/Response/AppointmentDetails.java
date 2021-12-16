package com.example.dto.Response;

import lombok.Data;

@Data
public class AppointmentDetails {
    private Integer appointmentId;
    private Integer centerId;
    private Integer slotId;
    private String dose;
    public AppointmentDetails(Integer appointmentId, Integer centerId, Integer slotId, String dose) {
        this.appointmentId = appointmentId;
        this.centerId = centerId;
        this.slotId = slotId;
        this.dose = dose;
    }
    public AppointmentDetails() {

    }
}
