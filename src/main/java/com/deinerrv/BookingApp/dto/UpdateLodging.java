package com.deinerrv.BookingApp.dto;

import com.deinerrv.BookingApp.entity.LodgingStatus;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLodging {

    @Size(min = 10 , max = 100, message = "Lodging's name must be between 10 and 100 characters")
    private String name;

    @Size(min = 10 , message = "Lodging's location must be over 10 characters")
    private String location;

    private Double latitude;
    private Double longitude;

    @Size(max = 2000 , message = "Lodging's description must be under 2000 characters")
    private String description;

    @Nullable
    @Size(max = 500 , message = "Lodging's rules must be under 500 characters")
    private String rules;

    @Positive(message = "capacity must be positive")
    private Integer capacity;

    @Positive(message = "bedrooms must be positive")
    private Integer bedrooms;

    @Positive(message = "bathrooms must be positive")
    private Double bathrooms;

    @Positive(message = "price must be positive")
    private Double price;

    private LodgingStatus status;

    private long typeId;
}
