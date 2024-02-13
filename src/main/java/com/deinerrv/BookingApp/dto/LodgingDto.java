package com.deinerrv.BookingApp.dto;

import com.deinerrv.BookingApp.entity.LodgingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LodgingDto {
    private long id;
    private String name;
    private String location;
    private double latitude;
    private double longitude;
    private String description;
    private String rules;
    private int capacity;
    private int bedrooms;
    private double bathrooms;
    private double price;
    private LodgingStatus status;
    private long ownerId;
    private String type;
}
