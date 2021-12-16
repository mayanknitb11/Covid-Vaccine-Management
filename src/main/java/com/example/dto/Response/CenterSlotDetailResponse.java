package com.example.dto.Response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CenterSlotDetailResponse {
    public CenterSlotDetailResponse(Integer centerId, String centerName, String city, String vaccine, List<SlotDetails> slotDetails) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.city = city;
        this.vaccine = vaccine;
        this.slotDetails = slotDetails;
    }

    private Integer centerId;
    private String centerName;
    private String city;
    private String vaccine;
    private List<SlotDetails> slotDetails;

}
