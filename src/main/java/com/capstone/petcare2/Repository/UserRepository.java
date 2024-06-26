package com.capstone.petcare2.Repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.petcare2.Models.User;
import com.capstone.petcare2.Models.UserModel;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
