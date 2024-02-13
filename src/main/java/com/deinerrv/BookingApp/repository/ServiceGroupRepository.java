package com.deinerrv.BookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deinerrv.BookingApp.entity.ServiceGroup;

@Repository
public interface ServiceGroupRepository extends JpaRepository<ServiceGroup,Long>{
    
}
