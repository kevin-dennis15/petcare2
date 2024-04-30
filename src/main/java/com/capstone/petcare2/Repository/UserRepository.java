package com.capstone.petcare2.Repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.petcare2.Models.UserModel;

public interface UserRepository extends JpaRepository<UserModel,UUID> {

}
