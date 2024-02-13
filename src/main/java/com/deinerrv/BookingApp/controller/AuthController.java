package com.deinerrv.BookingApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deinerrv.BookingApp.dto.AuthResponse;
import com.deinerrv.BookingApp.dto.LoginRequest;
import com.deinerrv.BookingApp.dto.RegisterRequest;
import com.deinerrv.BookingApp.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest ) throws RuntimeException{
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Successfully Registered",HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> getMethodName(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully",HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> postMethodName(@RequestBody LoginRequest login) {
        return new ResponseEntity<>(authService.login(login),HttpStatus.OK);
    }
    
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request) {
        return new ResponseEntity<>(authService.refreshToken(request),HttpStatus.OK);
    }
    
}
