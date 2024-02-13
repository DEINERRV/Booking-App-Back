package com.deinerrv.BookingApp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deinerrv.BookingApp.dto.ProfileInfo;
import com.deinerrv.BookingApp.dto.UpdateProfile;
import com.deinerrv.BookingApp.entity.User;
import com.deinerrv.BookingApp.mapper.UserMapper;
import com.deinerrv.BookingApp.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;

    @Transactional
    public void updateProfile(UpdateProfile updateDto, HttpServletRequest request){
        User user = authService.getUserFromRequest(request);
        if(updateDto.getName() != null)
            user.setName(updateDto.getName());
        if(updateDto.getLastName() != null)
            user.setLastName(updateDto.getLastName());
        if(updateDto.getPhone() != null)
            user.setPhone(updateDto.getPhone());

        userRepository.save(user);
    }

    public ProfileInfo getProfileInfo(HttpServletRequest request){
        return userMapper.UserToProfileInfo(authService.getUserFromRequest(request));
    }
}
