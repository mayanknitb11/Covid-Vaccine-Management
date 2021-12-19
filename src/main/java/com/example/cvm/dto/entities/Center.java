package com.example.cvm.dto.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String city;
    private Boolean covaxin;

    public Center() {
    }

    public Center(Integer id, String name, String city, Boolean covaxin) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.covaxin = covaxin;
    }


}
