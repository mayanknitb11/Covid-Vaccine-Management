package com.example.cvm.cvmController;

import com.example.cvm.dto.entities.Center;
import com.example.cvm.dto.entities.Slot;
import com.example.cvm.dto.entities.User;
import com.example.cvm.services.AppointmentService;
import com.example.cvm.services.CenterSlotServices;
import com.example.cvm.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DatabaseController {

    @Autowired
    UserServices u;
    @Autowired
    CenterSlotServices cs;
    @Autowired
    AppointmentService ap;

    @PostMapping("/add/user")
    public User addUser(@RequestBody User user) {
        return u.addUser(user);
    }

    @PostMapping("/add/center")
    public Center addCenter(@RequestBody Center req) {
        return cs.addCenter(req);
    }

    @PostMapping("/add/slot")
    public Slot addSlot(@RequestBody Slot req) {
        return cs.addSlot(req);
    }

    @DeleteMapping("delete/user/{userId}")
    public String delUser(@PathVariable("userId") int id) {
        return u.delUser(id);
    }

    @DeleteMapping("delete/center/{centerId}")
    public String delCenter(@PathVariable("centerId") int id) {
        return cs.delCenter(id);
    }

    @DeleteMapping("delete/slot/{slotId}")
    public String delSlot(@PathVariable("slotId") int id) {
        return cs.delSlot(id);
    }

    @PutMapping("update/slot")
    public Slot updateSlot(@RequestBody Slot slot) {
        return cs.updateSlot(slot);
    }

    @DeleteMapping("delete/appointment/{appintmentId}")
    public String delAppointment(@PathVariable("appintmentId") int id) { return ap.delAppointment(id);
    }

}
