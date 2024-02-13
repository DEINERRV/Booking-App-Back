package com.deinerrv.BookingApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deinerrv.BookingApp.dto.AuthResponse;
import com.deinerrv.BookingApp.dto.LoginRequest;
import com.deinerrv.BookingApp.dto.RegisterRequest;
import com.deinerrv.BookingApp.entity.Confirmation;
import com.deinerrv.BookingApp.entity.Role;
import com.deinerrv.BookingApp.entity.User;
import com.deinerrv.BookingApp.entity.UserStatus;
import com.deinerrv.BookingApp.mapper.UserMapper;
import com.deinerrv.BookingApp.repository.ConfirmationRepository;
import com.deinerrv.BookingApp.repository.RoleRepository;
import com.deinerrv.BookingApp.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final ConfirmationRepository confirmationRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest register){        
        if(userRepository.existsByEmailIgnoreCase(register.getEmail()))
            throw new RuntimeException("Email already exists"); 

        User user = userMapper.registerToUser(register);
        user.setStatus(UserStatus.DISABLED);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role normalRole = roleRepository.findByName("USER").orElseThrow(()-> new RuntimeException("Role not exists"));
        user.setRoles(List.of(normalRole));
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        Map<String,Object> variables = Map.of(
            "name",user.getName(),
            "url","http://localhost:8080/api/users/accountVerification/" + confirmation.getToken());
        
        mailService.sendHtmlMail("Verificate Account", "deiner.ra10@gmail.com", "mailTemplate", variables);
    }

    @Transactional
    public void verifyAccount(String token){
        Confirmation confirmation = confirmationRepository.findByToken(token).orElseThrow(()->new RuntimeException("Invalid Token"));
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail()).orElseThrow(()-> new RuntimeException("User not found"));
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = userRepository.findByEmailIgnoreCase(request.getEmail()).orElseThrow();
        String token = jwtService.genToken(user);
        String refreshToken = jwtService.genRefreshToken(user);
        return new AuthResponse(token, refreshToken);
    }

    @Transactional
    public AuthResponse refreshToken(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || !header.startsWith("Bearer "))
            throw new RuntimeException("No Refresh Token Found");
        
        String refreshToken = header.substring(7);
        String email = jwtService.getUsernameFromToken(refreshToken);
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new RuntimeException("Invalid Token"));

        if(!jwtService.isTokenValid(refreshToken, user))
            throw new RuntimeException("Invalid Token");

        AuthResponse response = new AuthResponse(jwtService.genRefreshToken(user), refreshToken);
        return response;
    }

    @Transactional
    public User getUserFromRequest(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.substring(7);

        String email = jwtService.getUsernameFromToken(token);
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new RuntimeException("Invalid Token"));
    }
}
