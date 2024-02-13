package com.deinerrv.BookingApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deinerrv.BookingApp.entity.Confirmation;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation,Long>{
    Optional<Confirmation> findByToken(String token);
}
