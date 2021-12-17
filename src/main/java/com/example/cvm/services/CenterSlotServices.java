package com.example.cvm.services;

import com.example.cvm.dto.DB.Appointment;
import com.example.cvm.dto.DB.Center;
import com.example.cvm.dto.DB.Slot;
import com.example.cvm.dto.DB.User;
import com.example.cvm.dto.Request.SlotDetailsRequest;
import com.example.cvm.dto.Response.CenterSlotDetailResponse;
import com.example.cvm.dto.Response.SlotDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CenterSlotServices {

    public List<CenterSlotDetailResponse> centerSlotServices(SlotDetailsRequest slotDetailsRequest, HashMap<Integer, User> user, HashMap<Integer, Center> center, HashMap<Integer, Slot> slot, HashMap<Integer, Appointment> appointment) {
        HashMap<Center, List<Slot>> map = new HashMap<>();
        for (Map.Entry<Integer, Slot> i : slot.entrySet()) {
            Slot s = i.getValue();
            if (s.getDate().equals(slotDetailsRequest.getDate())) {
                int centerId = s.getCenterId();
                if (center.containsKey(centerId)) {
                    Center c = center.get(centerId);
                    if (c.getCity().equals(slotDetailsRequest.getCity())) {
                        List<Slot> ss = new ArrayList<Slot>();
                        if (map.containsKey(c)) {
                            ss = map.get(c);
                        }
                        Boolean isDose1 = s.getDose1() && (slotDetailsRequest.getDose().equals("1"));
                        Boolean isDose2 = s.getDose2() && (slotDetailsRequest.getDose().equals("2"));

                        if (isDose1 || isDose2) {
                            ss.add(s);
                            map.put(c, ss);
                        }
                    }
                }
            }
        }
        List<CenterSlotDetailResponse> csDetails = new ArrayList<>();
        for (Map.Entry<Center, List<Slot>> i : map.entrySet()) {
            Center c = i.getKey();
            List<Slot> s = i.getValue();
            List<SlotDetails> slotList = new ArrayList<>();

            for (Slot j : s) {
                String dose = null;
                if (j.getDose1() && slotDetailsRequest.getDose().equals("1"))
                    dose = "1";
                else if (j.getDose2() && slotDetailsRequest.getDose().equals("2"))
                    dose = "2";
                else
                    return null;
                SlotDetails sd = new SlotDetails(j.getId(), dose, j.getTime(), j.getCovaxinAvailability());
                slotList.add(sd);
            }
            CenterSlotDetailResponse cs = new CenterSlotDetailResponse(c.getId(), c.getName(), c.getCity(), "Covaxin", slotList);
            csDetails.add(cs);
        }
        return csDetails;
    }
}
