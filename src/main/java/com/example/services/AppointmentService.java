package com.example.services;

import com.example.dto.DB.Appointment;
import com.example.dto.DB.Center;
import com.example.dto.DB.Slot;
import com.example.dto.DB.User;
import com.example.dto.Request.UpdateAppointmentRequest;
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
        String appointmentStatus = a.getAppointmentStatus();


        AppointmentResponse res = new AppointmentResponse(userId, u.getName(), u.getCity(), a.getAppointmentId(), s.getCenterId(), a.getSlotId(), s.getTime(), dose, vaccinationStatus, appointmentStatus);
        return res;
    }


    public AppointmentResponse updateAppointment (UpdateAppointmentRequest req, HashMap<Integer, User> user, HashMap<Integer, Center> center, HashMap<Integer, Slot> slot, HashMap<Integer, Appointment> appointment) {

        Integer appId = req.getAppointmentId();
        if (appointment.containsKey(appId)) {
            Appointment a = appointment.get(appId);
            if (a.getUserId().equals(req.getUserId())) {
                String centerStatus = req.getStatus();
                User u = user.get(req.getUserId());

                if(centerStatus.equals("SCHEDULED"))
                    return null;

                a.setAppointmentStatus(centerStatus);

                if (centerStatus.equals("FINISHED")) {
                    if (a.getDose1())
                        u.setDose1(true);
                    else if (a.getDose2())
                        u.setDose2(true);
                }
                else if(centerStatus.equals("CANCELED"))
                {
                    Slot s = slot.get(a.getSlotId());
                    s.setCovaxinAvailability(s.getCovaxinAvailability() +1);
                }

                return appointmentService(appId, user, center, slot, appointment);
            }
        }
        return null;
    }
}
