package com.example.services;

import com.example.dto.DB.Appointment;
import com.example.dto.DB.Center;
import com.example.dto.DB.Slot;
import com.example.dto.DB.User;
import com.example.dto.Request.BookingDetailsRequest;
import com.example.dto.Request.SlotDetailsRequest;
import com.example.dto.Response.CenterSlotDetailResponse;
import com.example.dto.Response.SlotDetails;
import com.example.dto.Response.UserDetails;

import java.util.HashMap;
import java.util.List;

public class BookingService {

    static int concat(int a, int b)
    {

        String s1 = Integer.toString(a);
        String s2 = Integer.toString(b);

        String s = s1 + s2;
        int c = Integer.parseInt(s);
        return c;
    }

    public UserDetails bookingService(BookingDetailsRequest bookingRequest, HashMap<Integer, User> user, HashMap<Integer, Center> center, HashMap<Integer, Slot> slot, HashMap<Integer, Appointment> appointment)
    {
        SlotDetailsRequest req = new SlotDetailsRequest(bookingRequest.getCity(),bookingRequest.getDose(),bookingRequest.getDate());
        CenterSlotServices c = new CenterSlotServices();
        List<CenterSlotDetailResponse> res = c.centerSlotServices(req,user,center,slot,appointment);

        for(CenterSlotDetailResponse i : res)
        {
            if(i.getCenterId().equals(bookingRequest.getCenterId()))
            {
                List<SlotDetails> list = i.getSlotDetails();
                for(SlotDetails j : list)
                {
                    if(j.getSlotId().equals(bookingRequest.getSlotId()))
                    {
                        //j.setVaccineAvailability(j.getVaccineAvailability() -1);
                        Slot ss = slot.get(bookingRequest.getSlotId());
                        ss.setCovaxinAvailability(ss.getCovaxinAvailability()-1);
                        Integer appointmentId = concat (bookingRequest.getUserId(), appointment.size());
                        Boolean isDose1 = (bookingRequest.getDose().equals("1"))?true:false;
                        appointment.put(appointmentId, new Appointment(appointmentId,bookingRequest.getUserId(),bookingRequest.getSlotId(),isDose1,!isDose1));

                        UserServices u = new UserServices();
                        return u.userServices(bookingRequest.getUserId(),user,center,slot,appointment);
                    }
                }
            }
        }


        return null;
    }
}
