package com.deinerrv.BookingApp.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLodging {
    @NotBlank(message = "lastName can't be blank")
    @Size(min = 10 , max = 100, message = "Lodging's name must be between 10 and 100 characters")
    private String name;

    @NotBlank(message = "lastName can't be blank")
    @Size(min = 10 , message = "Lodging's location must be over 10 characters")
    private String location;

    @NotNull(message = "location(latitude) can't be null")
    private double latitude;

    @NotNull(message = "location(longitude) can't be null")
    private double longitude;

    @Nullable
    @Size(max = 2000 , message = "Lodging's description must be under 2000 characters")
    private String description;

    @Nullable
    @Size(max = 500 , message = "Lodging's rules must be under 500 characters")
    private String rules;

    @NotNull(message = "capacity can't be null")
    @Positive(message = "capacity must be positive")
    private int capacity;

    @NotNull(message = "bedrooms can't be null")
    @Positive(message = "bedrooms must be positive")
    private int bedrooms;

    @NotNull(message = "bathrooms can't be null")
    @Positive(message = "bathrooms must be positive")
    private double bathrooms;

    @NotNull(message = "price can't be null")
    @Positive(message = "price must be positive")
    private double price;

    @NotNull(message = "type be null")
    private long typeId;

    private int[] servicesIds;
}
