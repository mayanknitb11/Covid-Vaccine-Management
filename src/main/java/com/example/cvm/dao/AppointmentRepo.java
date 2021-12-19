package com.example.cvm.dao;

import com.example.cvm.dto.entities.Appointment;
import com.example.cvm.dto.entities.Center;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepo extends CrudRepository<Appointment, Integer> {
    public Appointment findAById(Integer id);
    public List<Appointment> findByUserId (Integer id);
}
