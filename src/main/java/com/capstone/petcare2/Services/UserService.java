package com.capstone.petcare2.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.petcare2.Models.UserModel;
import com.capstone.petcare2.Repository.UserRepository;
import com.capstone.petcare2.Repository.UserRepository1;

@Service
public class UserService {

    @Autowired
    private UserRepository1 userRepository;

    // Add a new user
    public UserModel addUser(UserModel user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public UserModel updateUser(Long userId, UserModel newUser) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();
            existingUser.setUsername(newUser.getUsername());
            existingUser.setPassword(newUser.getPassword());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setFirstName(newUser.getFirstName());
            existingUser.setLastName(newUser.getLastName());
            existingUser.setPhoneNumber(newUser.getPhoneNumber());
            existingUser.setAddress(newUser.getAddress());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("UserModel with id " + userId + " not found.");
        }
    }

    // Delete a user by id
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // View a user by id
    public Optional<UserModel> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // View all users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}

