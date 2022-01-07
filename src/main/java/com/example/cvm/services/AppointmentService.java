package com.example.cvm.services;

import com.example.cvm.dao.AppointmentRepo;
import com.example.cvm.dao.SlotRepo;
import com.example.cvm.dao.UserRepo;
import com.example.cvm.dto.Request.UpdateAppointmentRequest;
import com.example.cvm.dto.Response.AppointmentResponse;
import com.example.cvm.dto.entities.Appointment;
import com.example.cvm.dto.entities.Slot;
import com.example.cvm.dto.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SlotRepo slotRepo;


    HashMap<Integer,Appointment> appList = new HashMap<>();
    public String delAppointment(Integer id) {
        appointmentRepo.deleteById(id);
        return "Deleted";
    }

    @Scheduled(fixedRate = 5000)
    public void getAppointments ()
    {
        System.out.println("Mayank");
        List<Appointment>list =  (List<Appointment>)appointmentRepo.findAll();
        for(Appointment a : list)
        {
            appList.put(a.getId(),a);
        }

    }

    public AppointmentResponse appointmentService(Integer appId) {

        Appointment a;
        if(appList.containsKey(appId))
        {
            System.out.println("From HashMap");
            a = appList.get(appId);
        }
        else{
            System.out.println("From DB");
            a = appointmentRepo.findAById(appId);
        }
        Integer userId = a.getUserId();
        Integer slotId = a.getSlotId();

        User u = userRepo.findAById(userId);
        Slot s = slotRepo.findAById(slotId);
        String dose = (a.getDose1()) ? "1" : "2";
        String vaccinationStatus = (u.getDose2()) ? "Vaccinated" : ((u.getDose1()) ? "Partially Vaccinated" : "Not Vaccinated");
        String appointmentStatus = a.getAppointmentStatus();

        AppointmentResponse res = new AppointmentResponse(userId, u.getName(), u.getCity(), a.getId(), s.getCenterId(), a.getSlotId(), s.getTime(), dose, vaccinationStatus, appointmentStatus);
        return res;
    }


    public AppointmentResponse updateAppointment(UpdateAppointmentRequest req) {

        Integer appId = req.getAppointmentId();
        Integer userId = req.getUserId();
        Appointment a = appointmentRepo.findAById(appId);
        if (a == null) return null;
        if (a.getUserId().equals(userId)) {
            String centerStatus = req.getStatus();
            String preStatus = a.getAppointmentStatus();
            User u = userRepo.findAById(userId);

            if (preStatus.equals(centerStatus)) return null;
            else if (centerStatus.equals("SCHEDULED")) return null;
            else if (centerStatus.equals("FINISHED")) {
                if (a.getDose1()) u.setDose1(true);
                else if (a.getDose2()) u.setDose2(true);
                userRepo.save(u);
            } else if (centerStatus.equals("CANCELED")) {
                Slot s = slotRepo.findAById(a.getSlotId());
                s.setCovaxinAvailability(s.getCovaxinAvailability() + 1);
                slotRepo.save(s);
            }

            a.setAppointmentStatus(centerStatus);
            appointmentRepo.save(a);

            return appointmentService(appId);
        }

        return null;
    }
}
