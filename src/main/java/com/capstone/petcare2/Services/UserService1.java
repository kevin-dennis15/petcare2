package com.capstone.petcare2.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.capstone.petcare2.DTO.ChangePasswordRequest;
import com.capstone.petcare2.DTO.LoginRequest;
import com.capstone.petcare2.DTO.Request;
import com.capstone.petcare2.DTO.Response;

@Component
public interface UserService1 {
    
    ResponseEntity<Response> signUp(Request request);
    ResponseEntity<Response> login(LoginRequest request);
    Response sendOtp();
    Response validateOtp();
    public Response resetPasswordWithOTP(String email, String otp, String newPassword);
    Response forgotPassword(String email);
    Response changePassword(ChangePasswordRequest request);
}
