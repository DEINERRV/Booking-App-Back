package com.deinerrv.BookingApp.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id", nullable = false)
    @JsonIgnore
    private ServiceGroup group;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Lodging> lodgings;
}
