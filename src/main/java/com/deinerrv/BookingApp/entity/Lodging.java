package com.deinerrv.BookingApp.entity;

import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lodging {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @Enumerated(EnumType.STRING)
    private LodgingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="type_id", nullable = false)
    private LodgingType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    User owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "lodging_service", 
    joinColumns = @JoinColumn(name = "lodging_id"), 
    inverseJoinColumns = @JoinColumn(name = "service_id"))
    Set<Service> services;
}
