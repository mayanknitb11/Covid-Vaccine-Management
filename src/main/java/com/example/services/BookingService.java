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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

    public UserDetails bookingService(BookingDetailsRequest bookingRequest, HashMap<Integer, User> user, HashMap<Integer, Center> center, HashMap<Integer, Slot> slot, HashMap<Integer, Appointment> appointment) {

        Integer slotId = bookingRequest.getSlotId();
        Integer centerId = bookingRequest.getCenterId();
        Center c = center.get(centerId);
        Slot s = slot.get(slotId);
        Boolean check = checkAvailableSlot(c,s, bookingRequest.getDate() ,s.getDate(), bookingRequest.getDose(),s.getDose1(),s.getDose2(),s.getCovaxinAvailability());
        Boolean eleigible = checkElegibility (appointment, bookingRequest.getUserId(), bookingRequest.getDose());
        System.out.println (check +" "+eleigible);
        if(check && eleigible)
        {
            String eleigibleForDose = (user.get(bookingRequest.getUserId()).getDose1())?((user.get(bookingRequest.getUserId()).getDose2())?"0":"2"):"1";
            s.setCovaxinAvailability(s.getCovaxinAvailability() - 1);
            Integer appointmentId = concat(bookingRequest.getUserId(), appointment.size());
            Boolean isBookingDose1 = bookingRequest.getDose().equals("1");
            Boolean isEleigibleDose1 = eleigibleForDose.equals("1");

            Boolean isappointmentDose1 = isBookingDose1 && isEleigibleDose1;
            Boolean isappointmentDose2 = (!isBookingDose1) && (!isEleigibleDose1);
            appointment.put(appointmentId, new Appointment(appointmentId, bookingRequest.getUserId(), bookingRequest.getSlotId(), isappointmentDose1, isappointmentDose2, "SCHEDULED"));

            UserServices u = new UserServices();
            return u.userServices(bookingRequest.getUserId(), user, center, slot, appointment);
        }
        return null;
    }


    static int concat(int a, int b) {

        String s1 = Integer.toString(a);
        String s2 = Integer.toString(b);

        String s = s1 + s2;
        int c = Integer.parseInt(s);
        return c;
    }


//    for user there should be no appointment for same dose

    static  Boolean checkElegibility (HashMap<Integer, Appointment> appointment, Integer userId, String bookingDose)
    {
        for(Map.Entry<Integer, Appointment> i : appointment.entrySet()) {
            Appointment a = i.getValue();
            if (a.getUserId().equals(userId))// && a.getAppointmentStatus().equals("FINISHED"))
            {
                String appDose = (a.getDose1()) ? "1" : "2";
                String status = a.getAppointmentStatus();

                if (bookingDose.equals("1")) {
                    if (appDose.equals("1") && (status == "SCHEDULE"))
                        return false;
                    else if (appDose.equals("1") && (status == "FINISHED"))
                        return false;
                } else if (bookingDose.equals("2")) {
                    if (appDose.equals("2") && (status == "SCHEDULE"))
                        return false;
                    else if (appDose.equals("2") && (status == "FINISHED"))
                        return false;
                    else if (appDose.equals("1") && (status == "SCHEDULE"))
                        return false;

                } else
                    return true;
            }
        }

        return (bookingDose.equals("1"))?true:false;
    }

    static Boolean checkAvailableSlot(Center c, Slot s, LocalDate bookingDate, LocalDate slotDate, String dose, Boolean isDose1, Boolean isDose2, Integer vaccineAvailability)
    {
        if(c == null || s == null)
            return false;
        if(((dose.equals("1") && isDose1) || (dose.equals("2") && isDose2)) && (vaccineAvailability >0) && slotDate.equals(bookingDate))
            return true;
        else
            return false;
    }

}
