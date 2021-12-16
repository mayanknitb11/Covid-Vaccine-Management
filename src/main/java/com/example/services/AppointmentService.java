package com.example.services;

import com.example.dto.DB.Appointment;
import com.example.dto.DB.Center;
import com.example.dto.DB.Slot;
import com.example.dto.DB.User;
import com.example.dto.Response.AppointmentResponse;

import java.util.HashMap;

public class AppointmentService {

    public AppointmentResponse appointmentService(Integer appId, HashMap<Integer, User> user, HashMap<Integer, Center> center, HashMap<Integer, Slot> slot, HashMap<Integer, Appointment> appointment) {
        Appointment a = appointment.get(appId);
        Integer userId = a.getUserId();

        User u = user.get(userId);
        Slot s = slot.get(a.getSlotId());
        String dose = (a.getDose1()) ? "1" : "2";
        String vaccinationStatus = (u.getDose2()) ? "Vaccinated" : (u.getDose1()) ? "Partially Vaccinated" : "Not Vaccinated";


        AppointmentResponse res = new AppointmentResponse(userId, u.getName(), u.getCity(), a.getAppointmentId(), s.getCenterId(), a.getSlotId(), s.getTime(), dose, vaccinationStatus);
        return res;


    }
}
