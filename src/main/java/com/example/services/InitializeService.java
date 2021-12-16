package com.example.services;

import com.example.dto.DB.Appointment;
import com.example.dto.DB.Center;
import com.example.dto.DB.Slot;
import com.example.dto.DB.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;


public class InitializeService {

    public String initializeMethod(HashMap<Integer, User> user, HashMap<Integer, Center> center, HashMap<Integer, Slot> slot, HashMap<Integer, Appointment> appointment) {

        User u1 = new User(101, "Mayank", "gwalior", false, false);
        User u2 = new User(102, "subham", "bangalore", false, false);
        User u3 = new User(103, "abhishek", "pune", false, false);
        User u4 = new User(104, "Mohan", "gwalior", false, false);
        user.put(101, u1);
        user.put(102, u2);
        user.put(103, u3);
        user.put(104, u4);

        Center c1 = new Center(901, "apollo", "gwalior", true);
        Center c2 = new Center(902, "link", "gwalior", true);
        Center c3 = new Center(903, "AIIMS", "bangalore", true);
        center.put(901, c1);
        center.put(902, c2);

        Slot s1 = new Slot(1001, true, true, LocalDate.now(), LocalTime.of(9, 0), 903, 55);
        Slot s2 = new Slot(1002, true, false, LocalDate.now(), LocalTime.of(12, 0), 901, 20);
        Slot s3 = new Slot(1003, false, true, LocalDate.now(), LocalTime.of(10, 30), 902, 10);
        Slot s4 = new Slot(1004, true, true, LocalDate.now(), LocalTime.of(3, 30), 901, 4);
        Slot s5 = new Slot(1005, false, true, LocalDate.now(), LocalTime.of(4, 30), 902, 40);
        slot.put(1001, s1);
        slot.put(1002, s2);
        slot.put(1003, s3);
        slot.put(1004, s4);
        slot.put(1005, s5);

//        Appointment a1 = new Appointment(5001, 101, 1001, true, false);
//        appointment.put(5001, a1);

        return "DATA READY";
    }

}
