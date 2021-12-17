package com.example.cvm.cvmController;

import com.example.cvm.dto.DB.Appointment;
import com.example.cvm.dto.DB.Center;
import com.example.cvm.dto.DB.Slot;
import com.example.cvm.dto.DB.User;
import com.example.cvm.dto.Request.BookingDetailsRequest;
import com.example.cvm.dto.Request.SlotDetailsRequest;
import com.example.cvm.dto.Request.UpdateAppointmentRequest;
import com.example.cvm.dto.Response.AppointmentResponse;
import com.example.cvm.dto.Response.CenterSlotDetailResponse;
import com.example.cvm.dto.Response.UserDetails;
import com.example.cvm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class CvmController {
    HashMap<Integer, User> user = new HashMap<Integer, User>();
    HashMap<Integer, Center> center = new HashMap<Integer, Center>();
    HashMap<Integer, Slot> slot = new HashMap<Integer, Slot>();
    HashMap<Integer, Appointment> appointment = new HashMap<Integer, Appointment>();

    @Autowired
    InitializeService i;
    @Autowired
    UserServices u;
    @Autowired
    CenterSlotServices c;
    @Autowired
    BookingService b;
    @Autowired
    AppointmentService a;


    @GetMapping("/")
    public String initialize() {
        return i.initializeMethod(user, center, slot, appointment);
    }

    @GetMapping("/user/{userId}")
    public UserDetails getUserDetails(@PathVariable("userId") int id) {
        if (user.containsKey(id)) {
            return u.userServices(id, user, center, slot, appointment);
        }
        return null;
    }

    @GetMapping("/slots")
    public List<CenterSlotDetailResponse> getSlots(@RequestBody SlotDetailsRequest req) {
        return c.centerSlotServices(req, user, center, slot, appointment);
    }

    @PostMapping("/bookSlot")
    public UserDetails bookSlot(@RequestBody BookingDetailsRequest req) {
        return b.bookingService(req, user, center, slot, appointment);
    }

    @GetMapping("/appointment/{appId}")
    public AppointmentResponse getAppDetails(@PathVariable("appId") int appId) {
        return a.appointmentService(appId, user, center, slot, appointment);
    }

    @PutMapping("/updateStatus")
    public AppointmentResponse updateStatus(@RequestBody UpdateAppointmentRequest req) {
        AppointmentService a = new AppointmentService();
        return a.updateAppointment(req, user, center, slot, appointment);
    }
}





