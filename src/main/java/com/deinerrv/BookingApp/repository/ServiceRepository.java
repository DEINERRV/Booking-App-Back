package com.deinerrv.BookingApp.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deinerrv.BookingApp.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Set<Service> findAllByGroupId(Long groupId);
    List<Service> findAllByLodgingsId(Long lodginId);
}
