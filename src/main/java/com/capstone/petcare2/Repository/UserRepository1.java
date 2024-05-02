package com.capstone.petcare2.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.petcare2.Models.UserModel;

public interface UserRepository1 extends JpaRepository<UserModel,Long>{
  Optional<UserModel> findById(Long id);

    void deleteById(Long id);
}
