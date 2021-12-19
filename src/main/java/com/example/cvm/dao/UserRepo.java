package com.example.cvm.dao;

import com.example.cvm.dto.entities.Center;
import com.example.cvm.dto.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    public User findAById(Integer id);
}
