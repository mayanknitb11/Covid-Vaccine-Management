package com.example.cvm.dto.DB;

import lombok.Data;

@Data
public class Appointment {

    private Integer appointmentId;
    private Integer userId;
    private Integer slotId;
    private Boolean Dose1;
    private Boolean Dose2;
    private String appointmentStatus;

    public Appointment(Integer appointmentId, Integer userId, Integer slotId, Boolean dose1, Boolean dose2, String appointmentStatus) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.slotId = slotId;
        Dose1 = dose1;
        Dose2 = dose2;
        this.appointmentStatus = appointmentStatus;
    }

}
