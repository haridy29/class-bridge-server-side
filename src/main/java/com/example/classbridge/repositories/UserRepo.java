package com.example.classbridge.repositories;

import com.example.classbridge.entities.User;
import jakarta.transaction.Transactional;

@Transactional
public interface UserRepo extends AbstractRepo<User> {

}
