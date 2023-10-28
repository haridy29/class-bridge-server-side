package com.example.classbridge.repositories;

import com.example.classbridge.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends AbstractRepo<User> {
}
