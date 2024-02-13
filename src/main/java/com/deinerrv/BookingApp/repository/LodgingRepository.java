package com.deinerrv.BookingApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deinerrv.BookingApp.entity.Lodging;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging,Long>, JpaSpecificationExecutor<Lodging>{
    Optional<Lodging> findByIdAndOwnerEmail(Long id, String ownerEmail); 
}
