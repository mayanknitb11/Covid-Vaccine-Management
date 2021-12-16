package com.example.dto.DB;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String name;
    private String city;
    private Boolean Dose1;
    private Boolean Dose2;

    public User() {
    }

    public User(Integer id, String name, String city, Boolean d1, Boolean d2) {
        this.id = id;
        this.name = name;
        this.city = city;
        Dose1 = d1;
        Dose2 = d2;
    }
}
