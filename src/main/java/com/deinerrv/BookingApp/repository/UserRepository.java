package com.deinerrv.BookingApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deinerrv.BookingApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByEmailIgnoreCase(String email);
}
