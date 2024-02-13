package com.deinerrv.BookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deinerrv.BookingApp.entity.LodgingType;

@Repository
public interface LodgingTypeRepository extends JpaRepository<LodgingType,Long>{
    
}
