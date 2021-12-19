package com.example.cvm.services;

import com.example.cvm.dao.CenterRepo;
import com.example.cvm.dao.SlotRepo;
import com.example.cvm.dto.Request.SlotDetailsRequest;
import com.example.cvm.dto.Response.CenterSlotDetailResponse;
import com.example.cvm.dto.Response.SlotDetails;
import com.example.cvm.dto.entities.Center;
import com.example.cvm.dto.entities.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CenterSlotServices {

    @Autowired
    CenterRepo centerRepo;
    @Autowired
    SlotRepo slotRepo;

    public Center addCenter(Center center) {
        Center c = centerRepo.save(center);
        return c;
    }

    public String delCenter(Integer id) {
        centerRepo.deleteById(id);
        return "Deleted";
    }

    public Slot addSlot(Slot slot) {
        Integer centerId = slot.getCenterId();
        Center c = centerRepo.findAById(centerId);
        if (c == null) return null;
        Slot s = slotRepo.save(slot);

        return s;
    }

    public String delSlot(Integer id) {
        slotRepo.deleteById(id);
        return "Deleted";
    }

    public Slot updateSlot(Slot slot) {
        Slot s = slotRepo.findAById(slot.getId());
        if (s == null) return null;

        slotRepo.save(slot);
        return s;
    }

    public Boolean filterList(Slot slot, String requireDose) {
        Boolean isDose1 = slot.getDose1() && (requireDose.equals("1"));
        Boolean isDose2 = slot.getDose2() && (requireDose.equals("2"));

        if ((isDose1 || isDose2) && slot.getCovaxinAvailability() > 0) return true;
        return false;
    }

    public List<CenterSlotDetailResponse> centerSlotServices(SlotDetailsRequest req) {

        HashMap<Center, List<Slot>> map = new HashMap<>();
        List<Slot> slotList = slotRepo.findByDate(req.getDate());
        slotList.removeIf(l -> !filterList(l, req.getDose()));
        for (Slot s : slotList) {
            Integer centerId = s.getCenterId();
            Center c = centerRepo.findAById(centerId);
            if (c == null) continue;
            else if (c.getCity().equals(req.getCity())) {
                List<Slot> ss = new ArrayList<Slot>();
                if (map.containsKey(c)) {
                    ss = map.get(c);
                }
                ss.add(s);
                map.put(c, ss);
            }
        }
        List<CenterSlotDetailResponse> csDetails = new ArrayList<>();
        for (Map.Entry<Center, List<Slot>> i : map.entrySet()) {
            Center c = i.getKey();
            List<Slot> s = i.getValue();
            List<SlotDetails> sl = new ArrayList<>();

            for (Slot j : s) {
                String dose = null;
                if (j.getDose1() && req.getDose().equals("1")) dose = "1";
                else if (j.getDose2() && req.getDose().equals("2")) dose = "2";
                else return null;
                SlotDetails sd = new SlotDetails(j.getId(), dose, j.getTime(), j.getCovaxinAvailability());
                sl.add(sd);
            }
            CenterSlotDetailResponse cs = new CenterSlotDetailResponse(c.getId(), c.getName(), c.getCity(), "Covaxin", sl);
            csDetails.add(cs);
        }
        return csDetails;
    }
}
