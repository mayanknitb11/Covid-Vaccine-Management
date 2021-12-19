package com.example.cvm.dto.Response;

import lombok.Data;

import java.util.List;

@Data
public class UserDetails {

    private Integer userId;
    private String name;
    private String city;
    private String vaccinationStatus;
    private List<AppointmentDetails> appointments;
    public UserDetails(Integer userId, String name, String city, String vaccinationStatus, List<AppointmentDetails> appointments) {
        this.userId = userId;
        this.name = name;
        this.city = city;
        this.vaccinationStatus = vaccinationStatus;
        this.appointments = appointments;
    }


}
