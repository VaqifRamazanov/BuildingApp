package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.enums.Role;
import com.matrix.buildingapp.exception.AlreadyExistException;
import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.jwt.JwtService;
import com.matrix.buildingapp.model.dto.requestDto.LoginReq;
import com.matrix.buildingapp.model.dto.requestDto.UserRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.LoginResponse;
import com.matrix.buildingapp.model.entity.Favorite;
import com.matrix.buildingapp.model.entity.User;
import com.matrix.buildingapp.repository.UserRepository;
import com.matrix.buildingapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();


    private final String myEmail = "vramazanov004@gmail.com";

    @Override
    public void register(UserRequestDto userRequestDto) {
        log.info("ActionLog.register.started: username {}", userRequestDto.getUsername());


        if( userRepository.findByUsername(userRequestDto.getUsername()).isPresent() ){
            log.warn("ActionLog.register.usernameAlreadyExists: username {}", userRequestDto.getUsername());
            throw new AlreadyExistException("Username is already taken.");
        }

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setEmail(userRequestDto.getEmail());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        Favorite favorite=new Favorite();
        favorite.setUser(user);
        user.setFavorite(favorite);

        userRepository.save(user);

        log.info("ActionLog.register.completed: username {}", userRequestDto.getUsername());
    }

    @Override
    public LoginResponse login(LoginReq loginReq) {
       log.info("ActionLog.login.started: username {}",loginReq.getUsername());
       authenticationManager
               .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(),loginReq.getPassword()));
       String token=jwtService.createToken(new User(loginReq.getUsername()));
       LoginResponse loginResponse=new LoginResponse();
       loginResponse.setToken(token);
       loginResponse.setUsername(loginReq.getUsername());

       log.info("ActionLog.login.success: username {}",loginReq.getUsername());
       return loginResponse;
    }

    @Override
    public void forgetPassword(String email) {
        log.info("ActionLog.resetPasswordOtp.started: email {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User email not found"));


        String otp = String.format("%04d", new Random().nextInt(10000));
        log.info("Generated OTP for {}: {}", email, otp);
        otpStorage.put(email, otp);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom(myEmail);
        msg.setSubject("Code Verification");
        msg.setText("Dear " + user.getUsername() + ",\n\nYour OTP for password reset is: " + otp +
                "\n\nThis OTP will expire in 10 minutes.\n\nRegards,\nBuilding.az");

        try {
            javaMailSender.send(msg);
            log.info("Successfully sent email to {}", email);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", email, e.getMessage());
            throw new RuntimeException("Failed to send email. Please try again later.");
        }
    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {
        log.info("ActionLog.resetPassword.started: email {}", email);

        String storedOtp = otpStorage.get(email);
        log.info("this is stored otp 1 {}",storedOtp);

        if (storedOtp == null) {
            log.warn("No OTP found for {}", email);
            throw new RuntimeException("No OTP found for this email");
        }
        log.info("this is stored otp 2 {}",storedOtp);

        if (!storedOtp.equals(otp)) {
            log.info("this is stored otp 3 {}",storedOtp);
            log.info("this is otp {}",otp);
            log.warn("Invalid OTP for {}", email);
            throw new RuntimeException("Invalid OTP");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User email not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        otpStorage.remove(email);

        log.info("ActionLog.resetPassword.completed: email {}", email);
    }
}
