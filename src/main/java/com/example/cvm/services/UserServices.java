package com.example.cvm.services;

import com.example.cvm.dao.AppointmentRepo;
import com.example.cvm.dao.SlotRepo;
import com.example.cvm.dao.UserRepo;
import com.example.cvm.dto.Response.AppointmentDetails;
import com.example.cvm.dto.Response.UserDetails;
import com.example.cvm.dto.entities.Appointment;
import com.example.cvm.dto.entities.Slot;
import com.example.cvm.dto.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepo userRepo;
    @Autowired
    SlotRepo slotRepo;
    @Autowired
    AppointmentRepo appointmentRepo;

    public User addUser(User req) {
        req.setDose1(false);
        req.setDose2(false);
        User res = userRepo.save(req);
        return res;
    }

    public String delUser(Integer id) {
        userRepo.deleteById(id);
        return "Deleted";
    }

    public UserDetails getUserDetailsById(int id) {

        if (userRepo.existsById(id)) {
            List<AppointmentDetails> app = new ArrayList<AppointmentDetails>();
            List<Appointment> appList = appointmentRepo.findByUserId(id);

            for (Appointment a : appList) {
                Slot slotdetails = slotRepo.findAById(a.getSlotId());
                String dose = (a.getDose1()) ? "1" : "2";
                AppointmentDetails appDetails = new AppointmentDetails(a.getId(), slotdetails.getCenterId(), a.getSlotId(), dose, a.getAppointmentStatus());
                app.add(appDetails);
            }

            User user = userRepo.findAById(id);
            String vaccinationStatus = (user.getDose2()) ? "Vaccinated" : (user.getDose1()) ? "Partially Vaccinated" : "Not Vaccinated";
            UserDetails userData = new UserDetails(id, user.getName(), user.getCity(), vaccinationStatus, app);
            return userData;
        }
        return null;
    }
}
