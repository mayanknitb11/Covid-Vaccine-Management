package com.example.cvm;

import com.example.dto.DB.Appointment;
import com.example.dto.DB.Center;
import com.example.dto.DB.Slot;
import com.example.dto.DB.User;
import com.example.dto.Request.BookingDetailsRequest;
import com.example.dto.Request.SlotDetailsRequest;
import com.example.dto.Response.AppointmentResponse;
import com.example.dto.Response.CenterSlotDetailResponse;
import com.example.dto.Response.UserDetails;
import com.example.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class CvmController {
    HashMap<Integer, User> user=new HashMap<Integer,User>();
    HashMap<Integer, Center> center=new HashMap<Integer,Center>();
    HashMap<Integer, Slot> slot=new HashMap<Integer,Slot>();
    HashMap<Integer, Appointment> appointment=new HashMap<Integer,Appointment>();

    @GetMapping("/")
    public String initialize ()
    {
        InitializeService i = new InitializeService();
        return i.initializeMethod(user,center,slot,appointment);
    }

    @GetMapping("/user/{userId}")
    public UserDetails getUserDetails (@PathVariable ("userId") int id)
    {
        if(user.containsKey(id)) {
            UserServices u = new UserServices();
            return u.userServices(id,user,center,slot,appointment);
        }
        return null;
    }

    @GetMapping("/slots")
    public List<CenterSlotDetailResponse> getSlots (@RequestBody SlotDetailsRequest slotDetailsRequest)
    {
        CenterSlotServices c = new CenterSlotServices();
        return c.centerSlotServices(slotDetailsRequest,user,center,slot,appointment);
    }

    @PostMapping("/bookSlot")
    public UserDetails bookSlot (@RequestBody BookingDetailsRequest bookingRequest)
    {
        BookingService b = new BookingService();
        return b.bookingService(bookingRequest,user,center,slot,appointment);
    }

    @GetMapping("/appointment/{appId}")
    public AppointmentResponse getAppDetails (@PathVariable ("appId") int appId)
    {

        AppointmentService a = new AppointmentService();
        return a.appointmentService(appId,user,center,slot,appointment);
    }
}





