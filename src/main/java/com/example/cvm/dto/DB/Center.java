package com.example.cvm.dto.DB;

import lombok.Data;

@Data
public class Center {

    private Integer id;
    private String name;
    private String city;
    private Boolean covaxin;

    public Center(Integer id, String name, String city, Boolean covaxin) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.covaxin = covaxin;
    }


}
