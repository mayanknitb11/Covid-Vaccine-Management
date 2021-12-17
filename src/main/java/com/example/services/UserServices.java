package com.example.services;

import com.example.dto.DB.Appointment;
import com.example.dto.DB.Center;
import com.example.dto.DB.Slot;
import com.example.dto.DB.User;
import com.example.dto.Response.AppointmentDetails;
import com.example.dto.Response.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServices {

    public UserDetails userServices(int id, HashMap<Integer, User> user, HashMap<Integer, Center> center, HashMap<Integer, Slot> slot, HashMap<Integer, Appointment> appointment) {
        List<AppointmentDetails> app = new ArrayList<AppointmentDetails>();
        for (Map.Entry<Integer, Appointment> i : appointment.entrySet()) {
            Appointment a = i.getValue();
            if (a.getUserId().equals(id)) {
                Slot slotdetails = slot.get(a.getSlotId());
                String dose = (a.getDose1()) ? "1" : "2";
                AppointmentDetails appDetails = new AppointmentDetails(a.getAppointmentId(), slotdetails.getCenterId(), a.getSlotId(), dose,a.getAppointmentStatus());
                app.add(appDetails);
            }
        }
        User user_Details;
        user_Details = user.get(id);
        String vaccinationStatus = (user_Details.getDose2()) ? "Vaccinated" : (user_Details.getDose1()) ? "Partially Vaccinated" : "Not Vaccinated";
        UserDetails userData = new UserDetails(user_Details.getId(), user_Details.getName(), user_Details.getCity(), vaccinationStatus, app);
        return userData;
    }
}
