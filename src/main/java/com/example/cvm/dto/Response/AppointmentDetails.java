package com.example.cvm.dto.Response;

import lombok.Data;

@Data
public class AppointmentDetails {
    private Integer appointmentId;
    private Integer centerId;
    private Integer slotId;
    private String dose;
    private String appointmentStatus;

    public AppointmentDetails(Integer appointmentId, Integer centerId, Integer slotId, String dose, String appointmentStatus) {
        this.appointmentId = appointmentId;
        this.centerId = centerId;
        this.slotId = slotId;
        this.dose = dose;
        this.appointmentStatus = appointmentStatus;
    }

    public AppointmentDetails() {

    }
}
