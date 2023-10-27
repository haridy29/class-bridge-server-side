package com.example.classbridge.services;

import com.example.classbridge.config.exception.ExceptionConfig;
import com.example.classbridge.entities.User;
import com.example.classbridge.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public User createUser(User user) {
        return userRepo.save(user);

    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
