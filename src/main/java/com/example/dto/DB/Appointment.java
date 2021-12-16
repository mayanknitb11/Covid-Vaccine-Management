package com.example.dto.DB;

import lombok.Data;

@Data
public class Appointment {

    private Integer appointmentId;
    private Integer userId;
    private Integer slotId;
    private Boolean Dose1;
    private Boolean Dose2;

    public Appointment(Integer appointmentId, Integer userId, Integer slotId, Boolean dose1, Boolean dose2) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.slotId = slotId;
        Dose1 = dose1;
        Dose2 = dose2;
    }

}
