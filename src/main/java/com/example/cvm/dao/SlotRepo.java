package com.example.cvm.dao;

import com.example.cvm.dto.entities.Center;
import com.example.cvm.dto.entities.Slot;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface SlotRepo extends CrudRepository<Slot, Integer> {
    public Slot findAById(Integer id);
    public List<Slot> findByDate(LocalDate date);
}