package  com.capstone.petcare2.Services.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.petcare2.DTO.ChangePasswordRequest;
import com.capstone.petcare2.DTO.EmailDetails;
import com.capstone.petcare2.DTO.LoginRequest;
import com.capstone.petcare2.DTO.Request;
import com.capstone.petcare2.DTO.Response;
import com.capstone.petcare2.DTO.UserInfo;
import com.capstone.petcare2.Models.User;
import com.capstone.petcare2.Models.UserModel;
import com.capstone.petcare2.Repository.UserRepository;
import com.capstone.petcare2.Repository.UserRepository1;
import com.capstone.petcare2.Services.UserService1;
import com.capstone.petcare2.Utils.AppUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService1 {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepository1 userRepository1;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;

    private Map<String, String> otpStorage = new HashMap<>();

    @Override
    public ResponseEntity<Response> signUp(Request request) {
        
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .statusCode(400)
                    .responseMessage("duplicate user")
                    .build());
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        User saveUser = userRepository.save(user);

        return ResponseEntity.ok(Response.builder()
                .statusCode(200)
                .responseMessage("Success")
                .userInfo(modelMapper.map(saveUser, UserInfo.class))
                .build());
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) {
        // Retrieve the user from the database using the provided email
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            // User found, check if the provided password matches the user's password
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                // Password matches, authentication successful
                // Generate and include token
                String token = tokenService.generateToken(user);
                // You can include additional logic here if needed
                return ResponseEntity.ok(Response.builder()
                        .statusCode(200)
                        .responseMessage("Login successful")
                        .token(token)
                        .build());
            } else {
                // Password does not match, authentication failed
                return ResponseEntity.badRequest().body(Response.builder()
                        .statusCode(401)
                        .responseMessage("Invalid email or password")
                        .build());
            }
        } else {
            // User not found, authentication failed
            return ResponseEntity.badRequest().body(Response.builder()
                    .statusCode(401)
                    .responseMessage("user not found")
                    .build());
        }
    }

    @Override
    public Response sendOtp() {
        return null;
    }

    @Override
    public Response validateOtp() {
        return null;
    }

    @Override
    public Response forgotPassword(String email) {
        // Check if the user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return Response.builder()
                    .statusCode(404)
                    .responseMessage("User not found")
                    .build();
        }

        // Generate OTP
        String otp = AppUtils.generateOtp();

        // Store the OTP for verification
        otpStorage.put(email, otp);

        // Prepare email details
        String subject = "Password Reset OTP";
        String messageBody = "Your OTP for password reset is: " + otp;
        EmailDetails emailDetails = new EmailDetails(email, subject, messageBody);

        // Send OTP to the user
        emailService.sendEmail(emailDetails);

        return Response.builder()
                .statusCode(200)
                .responseMessage("OTP sent successfully")
                .build();
    }

    public Response resetPasswordWithOTP(String email, String otp, String newPassword) {
        // Check if the OTP matches
        String storedOTP = otpStorage.get(email);
        if (storedOTP == null || !storedOTP.equals(otp)) {
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Invalid OTP")
                    .build();
        }

        // Update the password
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            // Clear the OTP from storage
            otpStorage.remove(email);
            return Response.builder()
                    .statusCode(200)
                    .responseMessage("Password changed successfully")
                    .build();
        } else {
            return Response.builder()
                    .statusCode(404)
                    .responseMessage("User not found")
                    .build();
        }
    }

    @Override
    public Response changePassword(ChangePasswordRequest request) {
        String email = request.getEmail();
        String oldPassword = request.getPassword();
        String newPassword = request.getNewPassword();

        // Check if the user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return Response.builder()
                    .statusCode(404)
                    .responseMessage("User not found")
                    .build();
        }

        // Validate if the old password matches
        User user = optionalUser.get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Invalid old password")
                    .build();
        }

        // Update the password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return Response.builder()
                .statusCode(200)
                .responseMessage("Password changed successfully")
                .build();
    }

}
