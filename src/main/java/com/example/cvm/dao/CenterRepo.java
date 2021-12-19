package com.example.cvm.dao;

import com.example.cvm.dto.entities.Center;
import org.springframework.data.repository.CrudRepository;

public interface CenterRepo extends CrudRepository<Center, Integer> {
    public Center findAById(Integer id);
}

