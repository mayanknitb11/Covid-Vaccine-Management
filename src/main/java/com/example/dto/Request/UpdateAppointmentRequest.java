package com.example.dto.Request;

import lombok.Data;

@Data
public class UpdateAppointmentRequest {
    private Integer appointmentId;
    private Integer userId;
    private String status;
}
