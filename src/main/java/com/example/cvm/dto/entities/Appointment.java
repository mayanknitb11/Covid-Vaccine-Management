package com.example.cvm.dto.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer slotId;
    private Boolean Dose1;
    private Boolean Dose2;
    private String appointmentStatus;

    public Appointment() {
    }

    public Appointment(Integer userId, Integer slotId, Boolean dose1, Boolean dose2, String appointmentStatus) {
        this.userId = userId;
        this.slotId = slotId;
        Dose1 = dose1;
        Dose2 = dose2;
        this.appointmentStatus = appointmentStatus;
    }

}
