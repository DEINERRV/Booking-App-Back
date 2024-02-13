package com.deinerrv.BookingApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.deinerrv.BookingApp.dto.ProfileInfo;
import com.deinerrv.BookingApp.dto.RegisterRequest;
import com.deinerrv.BookingApp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lodgings", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User registerToUser(RegisterRequest register);

    ProfileInfo UserToProfileInfo(User register);
}
