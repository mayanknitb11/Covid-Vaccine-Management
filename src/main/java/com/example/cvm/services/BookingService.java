package com.example.cvm.services;

import com.example.cvm.dao.AppointmentRepo;
import com.example.cvm.dao.CenterRepo;
import com.example.cvm.dao.SlotRepo;
import com.example.cvm.dao.UserRepo;
import com.example.cvm.dto.Request.BookingDetailsRequest;
import com.example.cvm.dto.Request.SlotDetailsRequest;
import com.example.cvm.dto.Response.CenterSlotDetailResponse;
import com.example.cvm.dto.Response.UserDetails;
import com.example.cvm.dto.entities.Appointment;
import com.example.cvm.dto.entities.Slot;
import com.example.cvm.dto.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService {

    @Autowired
    CenterRepo centerRepo;
    @Autowired
    SlotRepo slotRepo;
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    CenterSlotServices centerSlotServices;
    @Autowired
    UserServices userServices;

    public static SlotDetailsRequest makeReq(BookingDetailsRequest req) {
        SlotDetailsRequest slotReq = new SlotDetailsRequest(req.getCity(), req.getDose(), req.getDate());
        return slotReq;
    }

    static Boolean checkElegibility(List<Appointment> appointment, String bookingDose, Boolean isPartiallyVaccinated) {
        for (Appointment a : appointment) {
            String appDose = (a.getDose1()) ? "1" : "2";
            String status = a.getAppointmentStatus();

            if (bookingDose.equals("1") && appDose.equals("1") && (status.equals("SCHEDULED") || status.equals("FINISHED")))
                return false;

            else if (bookingDose.equals("2") && appDose.equals("2") && (status.equals("SCHEDULED") || status.equals("FINISHED")))
                return false;

            else if (appDose.equals("1") && (status.equals("SCHEDULED")))
                return false;
        }

        if (isPartiallyVaccinated)
            return (bookingDose.equals("2") ? true : false);
        return (bookingDose.equals("1")) ? true : false;
    }

    public UserDetails bookingService(BookingDetailsRequest bookingRequest) {

        List<CenterSlotDetailResponse> list = centerSlotServices.centerSlotServices(makeReq(bookingRequest));
        Integer userId = bookingRequest.getUserId();
        User user = userRepo.findAById(userId);
        Slot slot = slotRepo.findAById(bookingRequest.getSlotId());
        List<Appointment> appList = appointmentRepo.findByUserId(userId);

        String eleigibleForDose = (user.getDose1()) ? ((user.getDose2()) ? "0" : "2") : "1";
        Boolean isBookingDose1 = bookingRequest.getDose().equals("1");

        Boolean eleigible = checkElegibility(appList, bookingRequest.getDose(), user.getDose1());
        System.out.println(eleigible);
        if (eleigible) {
            slot.setCovaxinAvailability(slot.getCovaxinAvailability() - 1);
            Boolean isEleigibleDose1 = eleigibleForDose.equals("1");
            Boolean isappointmentDose1 = isBookingDose1 && isEleigibleDose1;
            Boolean isappointmentDose2 = (!isBookingDose1) && (!isEleigibleDose1);
            Appointment a = new Appointment(bookingRequest.getUserId(), bookingRequest.getSlotId(), isappointmentDose1, isappointmentDose2, "SCHEDULED");
            appointmentRepo.save(a);
            return userServices.getUserDetailsById(userId);
        }
        return null;
    }

}
