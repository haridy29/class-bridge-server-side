package com.example.classbridge.repositories;

import com.example.classbridge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AbstractRepo<T extends User> extends JpaRepository<T, String> {
    Optional<T> findByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByProfilePhone(String profile_phone);

    boolean existsByProfileEmail(String profile_email);

    T findByUsernameIgnoreCase(String username);

}
