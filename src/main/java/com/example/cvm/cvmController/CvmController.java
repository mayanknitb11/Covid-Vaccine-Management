package com.example.cvm.cvmController;

import com.example.cvm.dto.Request.BookingDetailsRequest;
import com.example.cvm.dto.Request.SlotDetailsRequest;
import com.example.cvm.dto.Request.UpdateAppointmentRequest;
import com.example.cvm.dto.Response.AppointmentResponse;
import com.example.cvm.dto.Response.CenterSlotDetailResponse;
import com.example.cvm.dto.Response.UserDetails;
import com.example.cvm.services.AppointmentService;
import com.example.cvm.services.BookingService;
import com.example.cvm.services.CenterSlotServices;
import com.example.cvm.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CvmController {

    @Autowired
    UserServices u;
    @Autowired
    CenterSlotServices c;
    @Autowired
    BookingService b;
    @Autowired
    AppointmentService a;


    @GetMapping("/user/{userId}")
    public UserDetails getUserDetails(@PathVariable("userId") int id) {
        return u.getUserDetailsById(id);
    }

    @GetMapping("/slots")
    public List<CenterSlotDetailResponse> getSlots(@RequestBody SlotDetailsRequest req) {
        return c.centerSlotServices(req);
    }

    @PostMapping("/bookSlot")
    public UserDetails bookSlot(@RequestBody BookingDetailsRequest req) {
        return b.bookingService(req);
    }

    @GetMapping("/appointment/{appId}")
    public AppointmentResponse getAppDetails(@PathVariable("appId") int appId) {
        return a.appointmentService(appId);
    }

    @PutMapping("/updateStatus")
    public AppointmentResponse updateStatus(@RequestBody UpdateAppointmentRequest req) {
        return a.updateAppointment(req);
    }
}





