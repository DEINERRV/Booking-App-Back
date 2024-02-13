package com.deinerrv.BookingApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deinerrv.BookingApp.dto.ProfileInfo;
import com.deinerrv.BookingApp.dto.UpdateProfile;
import com.deinerrv.BookingApp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileInfo> getProfileInfo(HttpServletRequest request) {
        return new ResponseEntity<>(userService.getProfileInfo(request),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> putMethodName(@RequestBody UpdateProfile updateDto, HttpServletRequest request) {
        userService.updateProfile(updateDto, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
