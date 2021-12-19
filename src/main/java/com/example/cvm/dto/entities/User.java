package com.example.cvm.dto.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public User(String name, String city, Boolean d1, Boolean d2) {
        this.name = name;
        this.city = city;
        Dose1 = d1;
        Dose2 = d2;
    }


}
