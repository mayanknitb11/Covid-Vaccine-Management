package com.example.cvm.dto.Response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class AppointmentResponse {

    private Integer id;
    private String name;
    private String city;
    private Integer appointmentId;
    private Integer centerId;
    private Integer slotId;
    private LocalTime time;
    private String dose;
    private String vaccinationStatus;
    private String appointmentStatus;
    public AppointmentResponse(Integer id, String name, String city, Integer appointmentId, Integer centerId, Integer slotId, LocalTime time, String dose, String vaccinationStatus, String appointmentStatus) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.appointmentId = appointmentId;
        this.centerId = centerId;
        this.slotId = slotId;
        this.time = time;
        this.dose = dose;
        this.vaccinationStatus = vaccinationStatus;
        this.appointmentStatus = appointmentStatus;
    }
}
